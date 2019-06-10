package com.xzj;

import java.util.HashMap;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 16:41 2019/6/5
 */
public class Score {
    public int point;


    @Override
    public boolean equals(Object obj) {
        Score bean=(Score)obj;
        if(bean.point==this.point){
            return true;
        }
        return false;
    }


    @Override
    public int hashCode() {
        return this.point;
    }

 /*   @Override
    public String toString() {
        return "Score{" +
                "point=" + point +
                '}';
    }*/
}
