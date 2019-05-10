package com.xzj.lerning.controller;

import com.xzj.lerning.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 11:27 2019/5/10
 */
@RestController
public class RoleController {
    @Autowired
    private UserClient userClient;

    @RequestMapping("test")
    public String test(){
        return userClient.getAllUserInfo();
    }
}
