package com.xzj.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 特点：
 * 1）固定的核心线程数大小,多余的线程存放在队列中；
 * 2）如果线程在执行任务中被终止，终止之前会创建其他的线程代替原来的；
 * 3）线程将会一直存在在线程池中，直到调用shutDown()方法
 */
public class FixedThreadPool {
    public static void main(String[] args) {
        Executors.newFixedThreadPool(5);
        new ThreadPoolExecutor(5,5,0, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());

    }
}
