package com.xzj.lerning.controller;

import com.xzj.lerning.aop.Performance;
import com.xzj.lerning.aop.PerformanceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private Performance performance;

    @RequestMapping(value ="aoptest" )
    public String test(){
        performance.perform();
        return "ok";
    }
}
