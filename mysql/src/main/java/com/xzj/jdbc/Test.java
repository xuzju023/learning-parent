package com.xzj.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 17:57 2019/6/11
 */
public class Test {
    public static void main(String[] args) throws Exception{
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        //Class.forName("com.mysql.cj.jdbc.Driver");
        dataSource.setDriverClass("com.mysqls.jdbc.Driver2");
        dataSource.setJdbcUrl("jdbc:mysql://devtest.ibr.cc:20010/br_sdk_pc?serverTimezone=UTC&useAffectedRows=true&useServerPrepStmts=false&rewriteBatchedStatements=true&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false");
        dataSource.setUser("br_pc");
        dataSource.setPassword("BR_sdk_pc123");
        dataSource.setCheckoutTimeout(60000);
        dataSource.setMaxStatementsPerConnection(20);
        dataSource.setInitialPoolSize(2);
        dataSource.setMaxIdleTime(60);
        dataSource.setMaxPoolSize(20);
        dataSource.setMinPoolSize(1);
        dataSource.setAcquireIncrement(1);
        dataSource.setIdleConnectionTestPeriod(60);
        dataSource.setNumHelperThreads(3);
        dataSource.setPreferredTestQuery("select 1");
        dataSource.setStatementCacheNumDeferredCloseThreads(1);
        dataSource.setUnreturnedConnectionTimeout(300);
        dataSource.getConnection();
        System.out.println(dataSource.getDriverClass());
    }
}
