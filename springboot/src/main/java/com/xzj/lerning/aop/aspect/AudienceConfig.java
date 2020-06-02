package com.xzj.lerning.aop.aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 14:37 2019/5/13
 */
@Configuration
public class AudienceConfig {
    @Bean
    public Audience init(){
        return new Audience();
    }
}
