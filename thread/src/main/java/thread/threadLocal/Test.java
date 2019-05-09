package thread.threadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 13:56 2019/5/9
 */
public class Test { 
    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i <5 ; i++) {
            exec.execute(new Accessor(i));
        }
        TimeUnit.MILLISECONDS.sleep(3);
        exec.shutdown();
    }
}
