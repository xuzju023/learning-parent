package com.xzj.aop.springAop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BuyAspectJ {
    @Pointcut("execution(* com.xzj.aop.springAop.IBuy.buy(..))")
    public void point(){}

    @Before("point()")
    public void haha(){
        System.out.println("男孩女孩都买自己喜欢的东西");
    }
}