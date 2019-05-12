package com.xzj.lerning.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class Audience {
    @Before(value="execution(**com.xzj.lerning.aop.Performance.perform(..))")
    public void beforePerform(){
        System.out.println("请保持手机静音");
    }

    @Before(value="execution(**com.xzj.lerning.aop.Performance.perform(..))")
    public void noTalking(){
        System.out.println("noTalking");
    }
}
