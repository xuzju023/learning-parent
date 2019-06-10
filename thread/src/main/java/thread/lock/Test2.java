package thread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test2 {
    private Lock lock = new ReentrantLock();
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    public static void main(String[] args)  {
        final Test2 test = new Test2();

        new Thread(){
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();

        new Thread(){
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();
    }

    public void insert(Thread thread) {
        if(lock.tryLock()){
            try {
                System.out.println(thread.getName()+"得到la锁");
                for(int i=0;i<5;i++) {
                    arrayList.add(i);
                }
            } catch (Exception e) {
            }finally {
                System.out.println(thread.getName()+"释放了锁");
                lock.unlock();
            }
        }else{
            System.out.println(thread.getName()+"获取锁失败");
        }
    }
}
