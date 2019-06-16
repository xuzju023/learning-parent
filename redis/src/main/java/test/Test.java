package test;

import constant.Constant;
import redis.clients.jedis.Jedis;

import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        Jedis redis = new Jedis(Constant.HOST);
        redis.flushAll();
        redis.zadd("view:user",1001,"sdf");
        redis.zadd("view:user",1002,"xvc");
        redis.zadd("view:user",1003,"555");
        //Long aLong = redis.zremrangeByRank("view:user", 0, 2);
        Set<String> zrange = redis.zrange("view:user", 0, 2);
       //Set<String> zrange = redis.zrevrange("view:user", 0, 2);
        System.out.println(redis.zcard("view:user"));
    }
}
