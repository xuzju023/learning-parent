package thread.jconsole;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 16:12 2019/6/21
 */
public class JconsoleTestProgram {
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }
    /**
     * 线程死循环演示
     */
    public static void createBusyThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)   // 第41行
                    ;
            }
        }, "testBusyThread");
        thread.start();
    }

    public static void fillHeap(int num) throws InterruptedException {
        Thread.sleep(20000); //先运行程序，在执行监控
        List<OOMObject> list = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++) {
            // 稍作延时，令监视曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws Exception {
      /*  fillHeap(1000);
        while(true){
            //让其一直运行着
        }*/

     //   createBusyThread();
        Object o1 = new Object();
        Object o2 = new Object();
        Mythread mythread = new Mythread(o1,o2,1);
        Mythread mythread2 = new Mythread(o1,o2,2);
        Mythread mythread3 = new Mythread(o1,o2,3);
        for (int i = 0; i <1; i++) {
            //new Thread(mythread).start();
        }

        for (int i = 0; i <1; i++) {
            //new Thread(mythread2).start();
        }

        for (int i = 0; i <1; i++) {
            //测试线程等待数
            new Thread(mythread3).start();
        }

    }



    //死锁
    static class Mythread implements Runnable{
        Object o1;
        Object o2;
        int flag;

        public Mythread(int flag){
            this.flag=flag;
        }
        public Mythread(Object o1, Object o2, int flag) {
            this.o1 = o1;
            this.o2 = o2;
            this.flag = flag;
        }

        @Override
        public void run() {
            if (flag == 1) {
                synchronized (o1) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                    synchronized (o2) {
                        System.out.println("-------o2---");
                    }
                }
            }
            if (flag == 2) {
                synchronized (o2) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                    synchronized (o1) {
                        System.out.println("-----o1-------");
                    }
                }
            }
            if(flag==3){
                try {
                    Mythread mythread = new Mythread(4);
                    Thread thread = new Thread(mythread);
                    thread.setName("线程3");
                    thread.start();
                    thread.join();
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(flag==4){
                try {
                    Mythread mythread = new Mythread(6);
                    Thread thread = new Thread(mythread);
                    Thread thread2 = new Thread(mythread);
                    Thread thread3 = new Thread(mythread);
                    thread.setName("n+1");
                    thread2.setName("n+1");
                    thread3.setName("n+1");
                    thread.start();
                    thread2.start();
                    thread3.start();
                    thread.join();
                    thread2.join();
                    thread3.join();
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(flag==5){
                try {
                    Mythread mythread = new Mythread(6);
                    Thread thread = new Thread(mythread);
                    thread.setName("线程5");
                    thread.start();
                    thread.join();
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(flag==6){
                try {
                    Thread.sleep(5000000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
