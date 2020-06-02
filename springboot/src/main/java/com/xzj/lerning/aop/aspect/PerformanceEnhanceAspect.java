package com.xzj.lerning.aop.aspect;

import com.xzj.lerning.aop.DefaultPerformanceProxyImpl;
import com.xzj.lerning.aop.PerformanceProxy;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 15:13 2019/5/13
 */
@Aspect
@Component
public class PerformanceEnhanceAspect {


    @DeclareParents(value = "com.xzj.lerning.aop.Performance+",defaultImpl = DefaultPerformanceProxyImpl.class)
    public static PerformanceProxy proxy;

}
