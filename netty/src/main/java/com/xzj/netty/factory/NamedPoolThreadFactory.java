package com.xzj.netty.factory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;


public final class NamedPoolThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNum = new AtomicInteger(1);

    private final AtomicInteger threadNum = new AtomicInteger(1);

    private final ThreadGroup group;
    private final String namePre;
    private final boolean isDaemon;

    public NamedPoolThreadFactory(String prefix) {
        this(prefix, true);
    }

    public NamedPoolThreadFactory(String prefix, boolean daemon) {
        SecurityManager manager = System.getSecurityManager();
        if (manager != null) {
            group = manager.getThreadGroup();
        } else {
            group = Thread.currentThread().getThreadGroup();
        }
        isDaemon = daemon;
        namePre = prefix + "-p-" + poolNum.getAndIncrement() + "-t-";
    }

    public Thread newThread(Runnable runnable) {
        Thread t = new Thread(group, runnable, namePre + threadNum.getAndIncrement(), 0);
        t.setContextClassLoader(NamedPoolThreadFactory.class.getClassLoader());
        t.setPriority(Thread.NORM_PRIORITY);
        t.setDaemon(isDaemon);
        return t;
    }

}
