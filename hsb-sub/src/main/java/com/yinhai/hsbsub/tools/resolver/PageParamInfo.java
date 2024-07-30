package com.yinhai.hsbsub.tools.resolver;

import java.lang.annotation.*;

/**
 * @Description 分页参数
 * @Author chenyl
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageParamInfo {
}
