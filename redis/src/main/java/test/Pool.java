package test;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 16:51 2019/5/5
 */
public class Pool {
    private static final Logger logger = LoggerFactory.getLogger(Pool.class);
    public static void main(String[] args) {
        Jedis jedis = getConnection();
        System.out.println(jedis);
        System.out.println(jedis.get("hello"));
        // 如果使用JedisPool，close操作不是关闭连接，代表归还连接池
        jedis.close();
    }

    public static Jedis getConnection() {
        // common-pool连接池配置，这里使用默认配置，后面小节会介绍具体配置说明
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        // 初始化Jedis连接池
        JedisPool jedisPool = new JedisPool(poolConfig, "192.168.5.161", 6379);
        // 设置最大连接数为默认值的5倍
        poolConfig.setMaxTotal(GenericObjectPoolConfig.DEFAULT_MAX_TOTAL * 5);
        // 设置最大空闲连接数为默认值的3倍
        poolConfig.setMaxIdle(GenericObjectPoolConfig.DEFAULT_MAX_IDLE * 3);
        // 设置最小空闲连接数为默认值的2倍
        poolConfig.setMinIdle(GenericObjectPoolConfig.DEFAULT_MIN_IDLE * 2);
        // 设置开启jmx功能
        poolConfig.setJmxEnabled(true);
        // 设置连接池没有连接后客户端的最大等待时间(单位为毫秒)
        poolConfig.setMaxWaitMillis(3000);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
