package com.xzj.aop.springAop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
//
@Configuration
@ComponentScan(basePackageClasses = {IBuy.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Config {
}
