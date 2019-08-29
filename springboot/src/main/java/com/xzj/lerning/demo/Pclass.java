package com.xzj.lerning.demo;

/**
 * @date 2019/8/21 18:19
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public abstract class Pclass implements IStudent {
    public  static  IStudent bean;
     abstract void hook();
    
    void print(){
        hook();
    }
}
