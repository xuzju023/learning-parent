package thread.pool;

import sun.java2d.SurfaceDataProxy;
import thread.lock.Test;

import java.nio.file.Watchable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: XuZhiJun
 * @Description: n个线程同时启动
 * @Date: Created in 17:14 2019/6/12
 */
public class ThreadPoolTest {

    public static CountDownLatch countDown = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    countDown.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis());

            }
        };
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(runnable);
        }
        Thread.sleep(1000l);
        countDown.countDown();
        threadPoolExecutor.shutdown();

    }

}
