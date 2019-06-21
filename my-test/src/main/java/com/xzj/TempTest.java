package com.xzj;

import java.util.ArrayList;
import java.util.concurrent.*;

public class TempTest {
    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        TestThread thread = new TestThread();
        ArrayList<Future> list = new ArrayList<>();
        for (int i = 0; i <1 ; i++) {
            list.add(service.submit(thread));
        }
        TimeUnit.SECONDS.sleep(1);
        list.get(0).cancel(true);
        service.shutdown();

    }
    static class TestThread implements Runnable{
        @Override
        public void run() {
        while (true){
            if(Thread.currentThread().isInterrupted()){
                System.out.println(Thread.currentThread().isInterrupted());
                return;
            }
            System.out.println(System.currentTimeMillis());
        }
        }
    }
}
