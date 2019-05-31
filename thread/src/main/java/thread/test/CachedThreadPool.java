package thread.test;

import thread.model.LiftOff;

import java.lang.annotation.Native;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
    public static void main(String[] args) {
        /*ExecutorService service= Executors.newCachedThreadPool();
        for (int i = 0; i <5 ; i++) {
            service.execute(new LiftOff());
        }
        service.shutdown();*/
        ArrayList<String> list = new ArrayList<>();
        int a=0;
        while (true){
            list.add(String.valueOf(a++));
            System.out.println(a);
        }
    }

}
