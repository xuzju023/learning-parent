package com.xzj.learning.controller;

import com.xzj.learning.service.UserRemoteService;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 9:40 2019/5/10
 */
@RestController
public class UserController implements UserRemoteService {
    @Override
    public String getAllUserInfo() {
        try {
            //TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "已获取所有用户信息";
    }
}
