package thread.model.atomic;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 9:44 2019/4/11
 */
public class AtomicIntegerTest extends Thread {
    private  int i=0;
    //private AtomicInteger i = new AtomicInteger(0);

    public  int getValue() {
        return i;
        //return i.get();
    }
    // test by add synchronized or not
    public synchronized void increment() {
        i++; //非原子操作 有3个步骤:1.读内存中的i 2.拷贝到高速缓存 3 执行并写入内存
        //i.getAndIncrement();
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            increment();
        }
    }

    public static void main(String[] args) throws InterruptedException {
       /* new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 5000);*/

        ExecutorService service = Executors.newCachedThreadPool();
        AtomicIntegerTest instance = new AtomicIntegerTest();
        for (int i = 0; i <10; i++) {
            service.execute(instance);
        }
        Thread.sleep(1000);
        Thread.sleep(1000);
        Thread.sleep(1000);
        System.out.println(instance.getValue());

    }

}
