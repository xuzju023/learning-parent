package com.xzj.lerning.aop;

import org.springframework.stereotype.Service;

@Service
public class PerformanceImpl implements Performance {
    @Override
    public void perform() {
        System.out.println("表演中");
        if(true){
            //throw new RuntimeException();
        }
    }

    @Override
    public void handClap(int number) {
        System.out.println("本场表演鼓掌次数: "+number);
    }
}
