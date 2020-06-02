package com.xzj.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 特点：
 * 1）按需创建新的线程，如果没有可用线程则创建新的线程,之前用过的线程可能会再次被使用；
 * 2）因为空闲线程会被移除线程池，因此，如果线程池长时间不被使用也不会消耗系统资源、
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        Executors.newCachedThreadPool();
        ThreadPoolExecutor cachedThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    }
}
