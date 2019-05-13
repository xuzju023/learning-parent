package com.xzj.lerning.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
//@Component
public class Audience {
    private Integer counter=0;

    @Pointcut(value="execution(* com.xzj.lerning.aop.Performance.perform(..))")
    public void pointCut(){}
    //@Before(value = "pointCut()")
    public void beforePerform(){
        System.out.println("请保持手机静音");
    }

    //@After(value = "pointCut()")
    public void noTalking(){
        System.out.println("鼓掌");
    }

    @Around(value = "pointCut()")
    public void around(ProceedingJoinPoint pj){
        try {
            System.out.println("请保持手机静音");
            //此方法可以反复执行,实现重试机制
            pj.proceed();
            System.out.println("鼓掌");
        } catch (Throwable throwable) {
            System.out.println("退票~");
        }
    }
    @After(value = "execution(* com.xzj.lerning.aop.Performance.handClap(int))&&args(number)")
    public void countHandClap(int number){
        counter+=number;
        System.out.println("总鼓掌次数: "+counter);
    }
}
