package com.xzj.learning.service;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 9:41 2019/5/10
 */
@RequestMapping(value="/user")
public interface UserRemoteService {
    @RequestMapping(value="get")
   public String getAllUserInfo();
}
