package com.xzj.lerning.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 11:51 2019/5/10
 */
@Component
public class ErrorClass implements UserClient {
    @Override
    public String getAllUserInfo() {
        return "超时啦！！！！！！！！！！！！";
    }
}
