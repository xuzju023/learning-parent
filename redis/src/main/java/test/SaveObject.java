package test;

import constant.Constant;
import model.Person;
import redis.clients.jedis.Jedis;

import java.io.*;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 11:03 2019/5/5
 */
public class SaveObject {

    public static void main(String[] args) {
        Jedis redis = new Jedis(Constant.HOST);
        Person person = new Person("xzj", "123");
        byte[] serialized = serialize(person);
        redis.set("serialized".getBytes(),serialized);
        byte[] bytes = redis.get("serialized".getBytes());
        Person person2 = (Person)unSerialize(bytes);
        System.out.println(person2);
    }
    //序列化
    public static byte[] serialize(Object obj){
        ObjectOutputStream obi=null;
        ByteArrayOutputStream bai=null;
        try {
            bai=new ByteArrayOutputStream();
            obi=new ObjectOutputStream(bai);
            obi.writeObject(obj);
            byte[] byt=bai.toByteArray();
            return byt;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //反序列化
    public static Object unSerialize(byte[] byt){
        ObjectInputStream oii=null;
        ByteArrayInputStream bis=null;
        bis=new ByteArrayInputStream(byt);
        try {
            oii=new ObjectInputStream(bis);
            Object obj=oii.readObject();
            return obj;
        } catch (Exception e) {

            e.printStackTrace();
        }


        return null;
    }
}
