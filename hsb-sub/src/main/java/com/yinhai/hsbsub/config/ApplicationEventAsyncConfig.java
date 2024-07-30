package com.yinhai.hsbsub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author almare
 * @date 2023-02-01 14:23
 * 事件异步执行配置
 */

@Configuration
public class ApplicationEventAsyncConfig {

    @Bean
    public ApplicationEventMulticaster applicationEventMulticaster() { //@1
        //创建一个事件广播器
        SimpleApplicationEventMulticaster result = new SimpleApplicationEventMulticaster();
        //设置异步执行器,来完成异步执行监听事件这样会导致所有的监听器都异步执行
        result.setTaskExecutor(fileBackupTaskExecutor());
        return result;
    }

    @Bean
    public Executor fileBackupTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //设置线程池参数信息
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setQueueCapacity(10);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setThreadNamePrefix("fileBackupEventHandle--");
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(60);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }
}
