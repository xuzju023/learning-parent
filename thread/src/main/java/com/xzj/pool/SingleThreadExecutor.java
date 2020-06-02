package com.xzj.pool;

import java.util.concurrent.*;

/**
 * 线程池中最多同时只有一个线程活跃
 * 同一时刻只有一个任务执行
 * 多余的任务放在LinkedBlockingQueue中
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
         Executors.newSingleThreadExecutor();
    }
}
