package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import constant.Constant;
import model.Person;
import redis.clients.jedis.Jedis;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 10:19 2019/5/5
 */
public class FiveDataStructure {
    public static void main(String[] args) {
        Jedis redis = new Jedis(Constant.HOST);
        //String
        redis.set("hello","world");
        redis.set("counter","1");
        redis.incr("counter");
        //hash
        redis.hset("hash","key","value");
        redis.hget("hash","key");//value
        redis.hgetAll("hash");//{key=value}
        //list
        redis.lpush("list","1","2","3");//[3, 2, 1]
        redis.rpush("list","1","2","3");//[1, 2, 3];
        //set 集合
        redis.sadd("set","a","b","c","a");
        redis.smembers("set");//[a, b, c] 不重复
        //zset 有序集合
        redis.zadd("zset",99,"xzj");
        redis.zadd("zset",60,"zhangsan");
        redis.zadd("zset",30,"lisi");
        redis.zrange("zset",0,1);//[xzj, lisi]
        redis.zrank("zset","lisi");//0
        redis.zrangeByScore("zset",0,99);
        System.out.println(redis.zrank("zset","zhangsan"));
    }

}
