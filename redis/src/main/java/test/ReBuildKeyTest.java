package test;

import redis.clients.jedis.Jedis;
import redis.clients.util.RedisInputStream;

/**
 * @Author: XuZhiJun
 * @Description:当一个KEY是一个热点KEY(高并发访问),如果key失效产生大量的热点key重建操作(可能是一系列复杂sql、多次I/O、多个依赖) 那么会造成后端服务压力增大甚至崩溃。解决办法：1.采用redis互斥锁 2.key永不过期(会带来对缓存一致性的要求,当一系列操作更新缓存的时候,其他请求获取到的是旧数据)
 * @Date: Created in 16:24 2019/6/3
 */
public class ReBuildKeyTest {
    public static String KEY = "hot-key";

    public static void main(String[] args) throws InterruptedException {
        get();
    }

    public static String get() throws InterruptedException {
        Jedis redis = new Jedis();
        String value = redis.get(KEY);
        if (value == null) {
            //设置redis互斥锁  TODO：使用java锁机制
            String mutexKey = "block" + KEY;
            if (0 == redis.setnx(mutexKey, "xxx")) {
                //获取value
                String res = "value";
                value=res;
                redis.set(KEY, value);
            } else {
                Thread.sleep(500l);
                //重新获取
                return get();
            }
        }
        return value;
    }
}
