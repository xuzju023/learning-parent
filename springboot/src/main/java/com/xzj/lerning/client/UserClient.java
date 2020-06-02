package com.xzj.lerning.client;

import com.xzj.lerning.service.UserRemoteService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 11:27 2019/5/10
 */
@FeignClient(name = "test-user-service",fallback = ErrorClass.class)
public interface UserClient {
    @RequestMapping(value = "/user/get",method = RequestMethod.GET)
    public String getAllUserInfo();
}
