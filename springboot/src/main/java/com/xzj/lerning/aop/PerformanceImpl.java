package com.xzj.lerning.aop;

import org.springframework.stereotype.Service;

@Service
public class PerformanceImpl implements Performance {
    @Override
    public void perform() {
        System.out.println("表演中");
        if(true){
            throw new RuntimeException();
        }
    }
}
