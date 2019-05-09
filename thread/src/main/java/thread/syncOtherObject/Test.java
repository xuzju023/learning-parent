package thread.syncOtherObject;

public class Test {
    public static void main(String[] args) {
        final  DualSync dualSync = new DualSync();
        new Thread(){
            @Override
            public void run(){
                dualSync.f();
            }
        }.start();
        dualSync.g();
    }
}
