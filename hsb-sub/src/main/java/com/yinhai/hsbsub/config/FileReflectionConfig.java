package com.yinhai.hsbsub.config;

import com.yinhai.hsbsub.properties.FileProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class FileReflectionConfig implements WebMvcConfigurer {
    @Resource
    FileProperties fileProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/program/**").addResourceLocations("file:"+fileProperties.getProgramFile());
        registry.addResourceHandler("/img/**").addResourceLocations("file:"+fileProperties.getImgFile());
        registry.addResourceHandler("/template/**").addResourceLocations("classpath:/template/");
    }
}
