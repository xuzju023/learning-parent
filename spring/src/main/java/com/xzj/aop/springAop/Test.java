package com.xzj.aop.springAop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//3
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Boy boy = context.getBean("boy",Boy.class);
        Girl girl = (Girl) context.getBean("girl");
        boy.buy();//
        girl.buy();

    }
}
