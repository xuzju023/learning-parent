package com.xzj.lerning.service;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 9:41 2019/5/10
 */
@RequestMapping("/user")
public interface UserRemoteService {
    @RequestMapping("get")
   public String getAllUserInfo();
}
