package com.xzj.lerning.aop;

import org.springframework.stereotype.Component;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 15:19 2019/5/13
 */
@Component
public class DefaultPerformanceProxyImpl implements  PerformanceProxy{

    @Override
    public void performEncore(Performance performance) {
        performance.perform();
        System.out.println("再表演一次");
    }
}
