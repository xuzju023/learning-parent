package com.xzj.lerning.demo;

/**
 * @date 2019/8/21 18:26
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class Cclass extends Pclass {
    @Override
    public IStudent getInstance() {
        bean = new Cclass();
        return bean;
    }

    @Override
    void hook() {
        System.out.println("ok~");
    }

    public static void main(String[] args) {
        Cclass bean = new Cclass();
        bean.hook();
    }
}
