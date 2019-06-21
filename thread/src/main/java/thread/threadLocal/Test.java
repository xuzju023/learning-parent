package thread.threadLocal;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.text.StyledEditorKit;
import java.util.Random;
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
        TimeUnit.SECONDS.sleep(1);
        exec.shutdownNow();
       /* for (int i = 0; i <3 ; i++) {
            exec.execute(new Thread(){

                @Override
                public void run() {
                    System.out.println(ThreadLocalVariableHolder.get());
                }
            });
        }*/
        //System.out.println(ThreadLocalVariableHolder.get());
       // System.out.println(ThreadLocalVariableHolder.get());
       // System.out.println(ThreadLocalVariableHolder.get());
    }
}
