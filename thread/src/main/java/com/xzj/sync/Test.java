package com.xzj.sync;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        Thread test1 = new Thread(new Runnable() {
            public void run() {
                test.test1();
            }
        }, "test1");
        Thread test2 = new Thread(new Runnable() {
            public void run() {
                test.test2();
            }
        }, "test2");
        test1.start();
        test2.start();
    }


    public void test1() {
        synchronized (Test.class) {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
            }
        }
    }

    public synchronized void test2() {
        int i = 5;
        while (i-- > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
            }
        }
    }
}
