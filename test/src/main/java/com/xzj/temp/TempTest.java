package com.xzj.temp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @date 2019/9/5 20:10
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class TempTest {

    public static void main(String[] args) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyMMdd HH:mm:ss");
        Date date2 = format.parse("20191104 20:26:00");
        Date date3 = format.parse("20191104 20:26:01");
        System.out.println(date3.getTime()-date2.getTime());
    }

    public static int[][] test(int len) {
        int[][] ints = new int[len][];
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                ints[0] = new int[1];
                ints[0][0] = 1;
                continue;
            }
            ints[i] = new int[i + 1];
            int[] preRow = ints[i - 1];
            int[] currRow = ints[i];
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    currRow[j] = preRow[0];
                } else if (j == i) {
                    currRow[j] = preRow[j - 1];
                } else {
                    currRow[j] = preRow[j - 1] + preRow[j];
                }
            }

        }
        return ints;
    }

    public static List<Integer> add(int raw, List<List<Integer>> list) {

        if (raw == 1) {
            List<Integer> res = list.get(0);
            res.add(1);
            return res;
        }
        List<Integer> currenRaw = list.get(raw - 1);
        List<Integer> preRaw = add(raw - 1, list);
        for (int i = 0; i < raw; i++) {
            if (i == 0) {
                currenRaw.add(0, preRaw.get(0));
            } else if (i == raw - 1) {
                currenRaw.add(i, preRaw.get(i - 1));
            } else {
                currenRaw.add(i, preRaw.get(i - 1) + preRaw.get(i));
            }

        }
        return currenRaw;
    }

    public static List<List<Integer>> generate(int numRow) {
        List<List<Integer>> origin = new ArrayList<>(numRow);
        if (numRow == 0) {
            return origin;
        }
        for (int i = 0; i < numRow; i++) {
            origin.add(new ArrayList<>());
        }
        add(numRow, origin);
        return origin;
    }


}
