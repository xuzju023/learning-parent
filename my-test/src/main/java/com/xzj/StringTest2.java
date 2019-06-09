package com.xzj;



public class StringTest2 {


    public static void main(String[] args) {
        String s = String.valueOf(123);
        String s1 = Integer.toString(123);
        //String s="123";
//        String s2=new String("123");
//        String s3=s2.intern();
//        String s4="123";
        System.out.println(s);
        System.out.println(s1);
    }
}
