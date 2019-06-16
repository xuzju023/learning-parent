package thread.stringBuilderAndBuffer;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 16:00 2019/6/4
 */
public class Test {

    public static void main(String[] args) throws  Exception{
        StringBuffer sb = new StringBuffer();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 1000l, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for (int i = 0; i <10; i++) {
            executor.execute(new TestThread(sb));
        }
        Thread.sleep(1000l);
        executor.shutdownNow();
        System.out.println(sb.length());
    }
   static class TestThread implements Runnable{
        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        private StringBuffer sb;

        public TestThread(StringBuffer sb) {
            this.sb = sb;
        }

        @Override
        public void run() {
            for (int i = 0; i <1000 ; i++) {
                sb.append("x");
            }
        }
    }
}
