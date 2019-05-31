package thread.interrupt;

import java.util.concurrent.*;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 9:47 2019/5/14
 */
public class Test {
    private static ExecutorService exec= Executors.newCachedThreadPool();
    static void test(Runnable r) throws InterruptedException{
        Future<?> future = exec.submit(r);
        TimeUnit.MILLISECONDS.sleep( 100);
        System.out.println("Interrupting "+r.getClass().getName());
        future.cancel(true);
        System.out.println("Interrupt sent to "+r.getClass().getName());
    }
    //结论:可以中断任何能产生InterruptException的线程 不能中断执I/O的线程 不能中断等待获取锁的线程
    public static void main(String[] args) throws Exception {
        test(new SleepBlocked());//可中断
        test(new IOBlocked(System.in));//不可中断
        test(new SynchronizedBlocked());//不可中断
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Aborting with System.exit(0)");
        System.exit(0);
    }
    //解决方案:关闭在线程上发生阻塞的底层资源(例如:stream.close() 本质是产生io异常)参考:closeResource
}
