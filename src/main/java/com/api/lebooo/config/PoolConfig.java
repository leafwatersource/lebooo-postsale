package com.api.lebooo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Date 2022-06-13 14:55
 */
@Configuration
@EnableAsync
public class PoolConfig {

    @Bean("psTaskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);   //初始化的线程数
        executor.setMaxPoolSize(20);    //线程池最大的线程数,只有缓冲队列满后
        executor.setQueueCapacity(100);  //缓冲队列
        executor.setKeepAliveSeconds(60);  //允许线程的空闲时间
        executor.setThreadNamePrefix("psTaskExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
