package com.xzj.test;

/**
 * 交替打印1-100输出到控制台
 */
public class Test1 {
    public static void main(String[] args) {
        /*只有一个TestThread对象*/
        TestThread t = new TestThread();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();
    }

    static class TestThread implements Runnable {
        int i = 1;
        @Override
        public void run() {
            while (true) {
                /*指代的为TestThread,因为使用的是implements方式。若使用继承Thread类的方式，慎用this*/
                synchronized (this) {
                    /*唤醒另外一个线程，注意是this的方法，而不是Thread*/
                    notify();
                    if (i <= 100) {
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                        i++;
                        try {
                            /*放弃资源，等待*/
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
