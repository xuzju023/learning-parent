package com.xzj.lerning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 10:40 2019/5/15
 */
@RestController
public class CurrencyTest {
    @RequestMapping("currencyTest")
    public String test(){
        RestTemplate restTemplate = new RestTemplate();
        ThreadPoolExecutor service = new ThreadPoolExecutor(10, 100, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        Runnable thread=new Runnable(){
            @Override
            public void run() {
                //1.返回responseEntity
                ResponseEntity<String> response = restTemplate.getForEntity("http://192.168.5.151:10001/role/test", String.class);
            }
        };
        for (int i = 0; i <30 ; i++) {
            service.execute(thread);
        }
        return "ok";
    }

    public static void main(String[] args) {
        System.out.println((double)1/1000/1000);
    }
}
