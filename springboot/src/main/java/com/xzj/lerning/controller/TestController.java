package com.xzj.lerning.controller;

import com.xzj.lerning.aop.Performance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class TestController {
    @Autowired
    private Performance performance;


    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list=null;
        Optional.ofNullable(list).ifPresent(item->{
            System.out.println("=---");
        });
    }
}
