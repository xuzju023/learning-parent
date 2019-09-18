package com.xzj.temp;

import java.util.HashMap;

/**
 * @date 2019/9/5 20:10
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class TempTest {

    public static void main(String[] args)throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("a","1");
        //key 存在返回key对应的value 不存在计算并返回新值
        String res = map.computeIfAbsent("b", value -> "2");
        System.out.println(res);
        System.out.println(map);

    }



}
