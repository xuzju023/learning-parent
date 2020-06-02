package test;

import constant.Constant;
import redis.clients.jedis.Jedis;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) {
        Jedis redis = new Jedis(Constant.HOST);
        redis.set("key1","1");
        redis.incr("key1");
        redis.set("key","1","2","NX",200l);
        System.out.println(redis.mget("key1"));
    }
}
