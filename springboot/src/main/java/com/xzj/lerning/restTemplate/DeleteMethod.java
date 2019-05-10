package com.xzj.lerning.restTemplate;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 10:07 2019/5/8
 */
public class DeleteMethod {

    public static void test(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.delete("http://USER-SERVICE?id={1}",13);
        HashMap<String, Object> params = new HashMap<>();
        params.put("id",13);
        restTemplate.delete("http://USER-SERVICE?id={id}",params);
    }

}
