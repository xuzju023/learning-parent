package thread.threadLocal;


/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 13:42 2019/5/9
 */
public class Accessor implements Runnable {
    private final int id;

    public Accessor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {

        return "#"+id+": "+ThreadLocalVariableHolder.get();
    }
}
