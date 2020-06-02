package com.xzj.aop.jdk;

import java.lang.reflect.Proxy;

public class JDKProxyTest {
    public static void main(String[] args) {
        MyInterfaceIml myInterfaceIml = new MyInterfaceIml();
        Object object = Proxy.newProxyInstance(myInterfaceIml.getClass().getClassLoader(), myInterfaceIml.getClass().getInterfaces(),new Handler(myInterfaceIml));
        Myinterface proxyInstance = (Myinterface) object;
        System.out.println(proxyInstance);
        proxyInstance.doSomething();

    }


}
