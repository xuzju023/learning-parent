package com.xzj.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Handler implements InvocationHandler {
    private Myinterface targetObj;

    public Handler(Myinterface targetObj) {
        this.targetObj = targetObj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass().getName());
        method.invoke(targetObj);
        System.out.println("调用后");
        return null;
    }
}
