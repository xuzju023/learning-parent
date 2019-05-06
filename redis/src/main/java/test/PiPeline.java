package test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.Set;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 17:19 2019/5/5
 */
//使用Pipeline模式，客户端可以一次性的发送多个命令，无需等待服务端返回。这样就大大的减少了网络往返时间，提高了系统性能。
public class PiPeline {
    public static void main(String[] args) {
        Jedis jedis = Pool.getConnection();
        Set<String> keys = jedis.keys("*");
        // 1)生成pipeline对象
        Pipeline pipeline = jedis.pipelined();
        // 2)pipeline执行命令，注意此时命令并未真正执行
        for (String key : keys) {
            pipeline.del(key);
        }
        // 3)执行命令
        pipeline.sync();

    }
}
