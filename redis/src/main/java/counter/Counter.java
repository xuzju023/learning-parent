package counter;

import constant.Constant;
import redis.clients.jedis.Jedis;

import java.util.concurrent.ConcurrentHashMap;

//计数器
public class Counter {
    public static void main(String[] args) {
        Jedis redis = new Jedis(Constant.HOST);
        //hash
        //redis.hset("counter:user","user02","0");
        redis.hincrBy("counter:user","user01",20);
        String res = redis.hget("counter:user", "user01");
        System.out.println(res);
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
    }
}
