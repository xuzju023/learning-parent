package com.xzj;


import sun.plugin.cache.CacheUpdateHelper;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 10:32 2019/6/3
 */
public class StringTest {

    public static void main(String[] args) {
        //              01234567890123
        String str = "www.oracle.com";
         /*
          * 使用数字表示范围，都是含头不含尾，
          * 例如从4开始，到10结束
          */
        String sub = str.substring(4, 10);
        System.out.println(sub);//oracle
          /*
           * 只有一个数字表示从这开始一直到结束
           */
        String sub1 = str.substring(4);
        System.out.println(sub1);//oracle.com

        /**
         * 获取www.oracle.com的域名
         *
         */
        int start = str.indexOf(".");
        //int end = str.lastIndexOf(".");
        int end = str.indexOf(".", start + 1);
        sub = str.substring(start + 1, end);
        System.out.println("域名：" + sub);
    }

}
