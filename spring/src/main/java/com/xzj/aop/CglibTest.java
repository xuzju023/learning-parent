package com.xzj.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibTest {
    public static void main(String[] args) {
        final SomeServiceImpl someService = new SomeServiceImpl();

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(someService.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("=======before");
                methodProxy.invoke(someService, objects);
                System.out.println("=======after");
                return null;
            }
        });
        SomeServiceImpl proxy = (SomeServiceImpl) enhancer.create();
        SomeServiceImpl proxy2 = (SomeServiceImpl) enhancer.create();
        System.out.println(proxy.getClass().getName());
        System.out.println(proxy2.getClass().getName());
        //proxy.doSome();
       // System.out.println(proxy2);
    }

    static class SomeServiceImpl {
        void doSome() {
            System.out.println("do something");
        }

        @Override
        public String toString() {
            return "SomeServiceImpl{}";
        }
    }
}
