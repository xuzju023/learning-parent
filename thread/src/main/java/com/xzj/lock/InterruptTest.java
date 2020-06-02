package com.xzj.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InterruptTest {

    public static void main(String[] args) throws Exception {
        new InterruptTest().test();
    }


    public void test() throws Exception {
        final Lock lock = new ReentrantLock();

        lock.lock();


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + " interrupted.");
                } catch (InterruptedException e) {
                    System.out.println("deal with InterruptedException");
                }
            }
        }, "child thread -1");

        t1.start();
        //Thread.sleep(1000);
        lock.unlock();

        //t1.interrupt();

        Thread.sleep(20000);
    }

}
