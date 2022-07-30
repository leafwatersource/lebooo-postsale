package com.api.lebooo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@MapperScan(basePackages = {"api.lebooo.dao"})
@SpringBootApplication
@EnableScheduling
@EntityScan(basePackages = "com.api.lebooo")//扫描项目注解, 等同于<context:component-scan />
@EnableCaching //开启缓存功能
public class PostsaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostsaleApplication.class, args);
    }

}
