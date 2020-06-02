package com.xzj.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.xzj.lifecycle");
        context.getBean("person");
    }
}
