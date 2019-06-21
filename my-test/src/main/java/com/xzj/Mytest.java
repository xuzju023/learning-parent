package com.xzj;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 17:43 2019/6/20
 */
public class Mytest {

    public static void main(String[] args) {
        try {
            String alarmCode="dsf_dsfdsa_dsf";
            int begin = alarmCode.indexOf("_")+1;
            int end = alarmCode.indexOf("_", begin);
            System.out.println(begin+"-"+end);
            System.out.println(alarmCode.substring(begin,end));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
