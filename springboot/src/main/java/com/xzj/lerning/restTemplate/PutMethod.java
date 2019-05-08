package com.xzj.lerning.restTemplate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 10:04 2019/5/8
 */
public class PutMethod {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        //put函数为void类型，没有返回内容，没有responseType参数，除此之外的其他传入参数定义与用法与postForObject基本一致。
        //url requestBody
        restTemplate.put("",new User());
    }
}
