package com.api.lebooo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Date 2020/7/18 9:37
 */
@ControllerAdvice
public class GlobalExceptionConfig {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionConfig.class);

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e){
        logger.error("---------------------系统异常【全局异常】---------------------："+ e.getMessage(),e);
        return "error";
    }
}
