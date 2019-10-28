package com.xzj.config;

import com.ctrip.framework.apollo.ConfigFile;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.core.enums.ConfigFileFormat;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.File;

/**
 * @date 2019/10/14 15:38
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
@Configuration
@MapperScan(basePackages = "com.xzj.mapper",sqlSessionFactoryRef = "sqlSessionFactory")
public class ShardingConfig {
    @Bean
    public DataSource dataSource() throws Exception {
        Resource resource = new ClassPathResource("sharding-config.yml");
        File file = resource.getFile();
        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(file);
        return dataSource;
    }

    public DataSource dataSourceFromNamespace() throws Exception {
        ConfigFile configFile = ConfigService.getConfigFile("namespace-test", ConfigFileFormat.YML);
        String content = configFile.getContent();
        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(content.getBytes("UTF-8"));
        return dataSource;
    }
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setMapperLocations(// 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        return new DataSourceTransactionManager(dataSource());
    }
}
