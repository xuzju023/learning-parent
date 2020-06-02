package com.xzj.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) throws Exception {
        ReentrantLock lock = new ReentrantLock();
        Mythread mythread = new Mythread(lock);
        Thread thread1 = new Thread(mythread);
        Thread thread2 = new Thread(mythread);
        Thread thread3 = new Thread(mythread);
        thread1.start();
        thread2.start();
        thread3.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(thread3.getState());
    }

    static class Mythread implements Runnable {
        private Lock lock;
        private int flag = 0;

        public Mythread(ReentrantLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                lock.lock();
                while (true){}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
