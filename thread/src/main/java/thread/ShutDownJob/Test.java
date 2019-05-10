package thread.ShutDownJob;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 16:34 2019/5/9
 */
public class Test {
    public static void main(String[] args) throws Exception{
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            service.execute(new Entrance(i));
        }
        TimeUnit.SECONDS.sleep(3);
        Entrance.cancel();
        service.shutdown();
        if(!service.awaitTermination(250,TimeUnit.MILLISECONDS)){
            System.out.println("Some tasks were not terminated!");
            System.out.println("Total:"+Entrance.getTotalCount());
            System.out.println("Sum of Entrances:"+Entrance.sumEntrances());
        }
    }
}
