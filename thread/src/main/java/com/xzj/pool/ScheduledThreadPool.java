package com.xzj.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


/**
 * 阻塞队列使用DelayedWorkQueue
 * 特点：
 * 1）核心线程数将会一直存在线程池中，除非设置了allowCoreThreadTimeOut
 * 2）可以设置线程的执行时间
 */
public class ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
    }
}
