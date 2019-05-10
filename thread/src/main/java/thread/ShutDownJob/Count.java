package thread.ShutDownJob;

import sun.font.TrueTypeFont;

import java.util.Random;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 16:09 2019/5/9
 */
public class Count {
    private int count=0;
    private Random rand = new Random(47);

    public synchronized int increment(){
        int temp=count;
        if(rand.nextBoolean()){
            Thread.yield();
        }
        return (count=++temp);
    }
    public synchronized int value(){
        return count;
    }
}
