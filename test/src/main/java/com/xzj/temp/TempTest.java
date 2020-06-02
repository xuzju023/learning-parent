package com.xzj.temp;

import com.xzj.model.Man;
import com.xzj.model.Student;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @date 2019/9/5 20:10
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class TempTest implements TempInterface{
    public static void main(String[] args) throws Exception {
//        byte[]classFile = ProxyGenerator.generateProxyClass("Proxy0",Student.class.getInterfaces());
//
//        File file =new File("D:\\test\\Proxy0.class");
//
//        FileOutputStream fos =new FileOutputStream(file);
//
//        fos.write(classFile);
//
//        fos.flush();
//
//        fos.close();

        Student student = new Student();
        ProxyHandler proxyHandler = new ProxyHandler(student);
        Man studentProxy0 = (Man) Proxy.newProxyInstance(TempTest.class.getClassLoader(), Student.class.getInterfaces(), proxyHandler);
        studentProxy0.getName();
        System.out.println("end");

    }


}
