package thread.model;

import java.util.ArrayList;

public class LiftOff {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        list.add(1);
        list2.addAll(list);
        list.add(2);
        System.out.println(list);
        System.out.println(list2);
    }
}
