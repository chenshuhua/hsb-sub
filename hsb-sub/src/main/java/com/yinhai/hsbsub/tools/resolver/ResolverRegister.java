package com.yinhai.hsbsub.tools.resolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Description
 * @Author chenyl
 **/
@Configuration
public class ResolverRegister implements WebMvcConfigurer {
    @Bean
    public PageParamInfoResolver pageParamInfoResolver() {
        return new PageParamInfoResolver();
    }


    /**
     * 自定义参数解析器注册
     * @Author  chenyl
     *
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(pageParamInfoResolver());
    }
}
