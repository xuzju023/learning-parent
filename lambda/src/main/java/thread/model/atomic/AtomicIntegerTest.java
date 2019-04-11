package thread.model.atomic;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 9:44 2019/4/11
 */
public class AtomicIntegerTest extends Thread{
    private  int i=0;
    //private AtomicInteger i=new AtomicInteger(0);
    public int getValue(){
        return i;
        //return i.get();
    }
    void increment(){
        i++;
        i++;
        //i.getAndIncrement(2);
    }

    @Override
    public void run() {
        while(true){
            increment();
        }
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 5000);

        ExecutorService service = Executors.newCachedThreadPool();
        AtomicIntegerTest instance = new AtomicIntegerTest();
        service.execute(instance);
        while (true){
            int next =instance.getValue();
            if(next%2!=0){
                System.out.println(next);
            }
        }
    }

}
