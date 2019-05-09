package thread.syncOtherObject;

public class DualSync {
    private Object syncObject=new Object();

    public synchronized void f(){
        for (int i = 0; i <10; i++) {
            System.out.println("f()");
            Thread.yield();
        }
    }
    public void g(){
        // f()方法同步对象是this 此处采用非另一个同步对象 保证主线程不阻塞
        synchronized(syncObject){
            for (int i = 0; i <10 ; i++) {
                System.out.println("g()");
                Thread.yield();
            }
        }
    }

}
