package com.xzj;


import java.io.*;

public class PathClassLoader extends ClassLoader {

    private String classPath;

    public PathClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getData(String className) {
        String path = classPath + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
        try {
            InputStream is = new FileInputStream(path);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int num = 0;
            while ((num = is.read(buffer)) != -1) {
                stream.write(buffer, 0, num);
            }
            return stream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static void main(String args[]) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        ClassLoader pcl = new PathClassLoader("G:");
        Class c = pcl.loadClass("com.xzj.TestClassLoad");//注意要包括包名
        System.out.println(c.newInstance());//打印类加载成功.
    }
}

