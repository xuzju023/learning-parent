package com.xzj.lifecycle;

import org.springframework.context.annotation.Bean;

//@Configuration
public class Config {
    @Bean(initMethod = "init")
    public Person person(){
        return new Person();
    }
}
