package com.xzj.classloader;

public class Test {
    public static int v1= 1;

    public Test(){

        System.out.println("调用到了test");

        System.out.println("test加载器为："+this.getClass().getClassLoader());

    }

}
