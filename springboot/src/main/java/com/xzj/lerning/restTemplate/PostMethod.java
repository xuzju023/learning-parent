package com.xzj.lerning.restTemplate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 9:46 2019/5/8
 */
public class PostMethod {

    public static void test(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        //url requestBody responseType
        ResponseEntity<String> response = restTemplate.postForEntity("http://~~~", new User(), String.class);
        Map<String,String> map = restTemplate.postForObject("http://~~~", new User(), Map.class);
    }

}
