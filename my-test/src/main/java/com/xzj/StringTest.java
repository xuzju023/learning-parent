package com.xzj;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 10:32 2019/6/3
 */
public class StringTest {
    public static void main(String[] args) {
        String a="xzj"+"s";
        String b="xzj"+"s";
        String c=new String("zj");
        String d=new String("zj");
        System.out.println(a==b);
    }
}
