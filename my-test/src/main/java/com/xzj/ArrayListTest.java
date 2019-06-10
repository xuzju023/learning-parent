package com.xzj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 14:27 2019/6/5
 */
public class ArrayListTest {
    public static void main(String[] args) throws Throwable {
        Score score1 = new Score();
        Score score2 = new Score();
        score1.point=99;
        score2.point=99;
        HashSet<Object> set = new HashSet<>();
        set.add(score1);
        set.add(score2);
        System.out.println(set);
        System.out.println(score1==score2);
    }








    public static int get() {
        int a = 0;
        try{
            a = 2;
            System.out.println(1/0);
            return a;
        }catch (Exception e){

        }
        finally{
            System.out.println("It is in final chunk.");
            a++;
            return a;
        }
    }
}
