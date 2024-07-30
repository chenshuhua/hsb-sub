package com.yinhai.hsbsub.tools.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 只有拥有values中的至少一项权限才能访问被该注解标注的接口
 * @author chenyl
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {
    int level() default 0;
}
