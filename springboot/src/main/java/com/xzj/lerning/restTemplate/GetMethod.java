package com.xzj.lerning.restTemplate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 9:29 2019/5/8
 */
public class GetMethod {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        //1.返回responseEntity
        ResponseEntity<String> response = restTemplate.getForEntity("http://USER-SERVICE/user? name={1}", String.class, "xzj");
        String json = response.getBody();
        HashMap<String, String> params = new HashMap<>();
        params.put("name","xzj");
        //2.直接返回结果
        String json2 = restTemplate.getForObject("http://USER-SERVICE/user? name={name}", String.class, params);
    }
}
