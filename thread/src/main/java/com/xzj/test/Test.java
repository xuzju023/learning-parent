package com.xzj.test;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) throws Exception {
        ThreadTest thread = new ThreadTest();
        Thread thread1 = new Thread(thread);
        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(thread1.getState());
        TimeUnit.SECONDS.sleep(2);
        System.out.println(thread1.getState());
    }
    static class ThreadTest implements  Runnable{

        @Override
        public void run() {
            synchronized (this){
                try {
                    //System.out.println(Thread.currentThread().getName());
                    //TimeUnit.SECONDS.sleep(5);
                    wait();
                    while (true){}
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
