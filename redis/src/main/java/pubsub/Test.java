package pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @date 2019/9/26 20:57
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class Test {
    public static void main(String[] args) {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "192.168.1.154", 6379, 3000);

        Jedis jedis = pool.getResource();
        jedis.psubscribe(new KeyExpiredListener(), "__keyevent@*__:expired");
        JedisCluster jedisPool;
        pool.close();

    }
}
