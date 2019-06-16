package thread.threadLocal;

import java.util.Random;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 13:48 2019/5/9
 */
public class ThreadLocalVariableHolder {
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
        private Random rand = new Random(47);

        @Override
        protected synchronized Integer initialValue() {
            return rand.nextInt(10000);
        }
    };

    public static void increment() {
        value.set(value.get() + 1);
    }

    public static int get() {
        return value.get();
    }

}
