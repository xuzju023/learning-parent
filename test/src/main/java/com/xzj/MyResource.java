package com.xzj;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 指定注解保留的范围 (运行期)
@Retention(RetentionPolicy.RUNTIME)
// 允许注解标注的位置 (属性, 方法)
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.TYPE })
public @interface MyResource {
    // 提供name属性
    public String name() default "";
}
