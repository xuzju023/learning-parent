package thread.sync;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class PairManager {
     AtomicInteger checkCounter=new AtomicInteger(0);
     protected Pair p=new Pair();
     private List<Pair> storage= Collections.synchronizedList(new ArrayList<>());

     public synchronized Pair getPair(){
         return new Pair(p.getX(),p.getY());
     }
     protected void store(Pair p){
         storage.add(p);
         try {
             TimeUnit.MILLISECONDS.sleep(50);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }

     public abstract void increment();
}
