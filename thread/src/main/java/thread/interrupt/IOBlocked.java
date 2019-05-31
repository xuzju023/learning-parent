package thread.interrupt;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 9:52 2019/5/14
 */
public class IOBlocked implements Runnable {
   private InputStream in;
   public IOBlocked(InputStream in){
       this.in=in;
   }

    @Override
    public void run() {
        try {
            System.out.println("Waiting for read():");
            System.out.println("是否中断:"+Thread.currentThread().isInterrupted());
            in.read();
            System.out.println("是否中断:"+Thread.currentThread().isInterrupted());
        } catch (IOException e) {
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Interrupted form blocked I/O");
            }else{
                throw new RuntimeException(e);
            }
        }
        System.out.println("Exiting IOBlocked.run()");
    }
}
