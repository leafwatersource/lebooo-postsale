package com.api.lebooo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/7/15 15:23
 */
@Configuration
public class WebMvcConfigurerConfig implements WebMvcConfigurer {

    @Value("${file.firmware.path}")
    private String fileFirmwarePath;

    @Value("${upload.filepath}")
    private String uediterFilepath;

    @Bean
    public HandlerInterceptor getApiHandlerInterceptorConfig(){
        return new ApiHandlerInterceptorConfig();
    }

    @Bean
    public HandlerInterceptor getConsoleHandlerInterceptorConfig(){
        return new ConsoleHandlerInterceptorConfig();
    }


    /*private CorsConfiguration corsConfiguration(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration());
        return new CorsFilter(source);
    }*/


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(getApiHandlerInterceptorConfig())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**","/error","/file/firmware/**","/file/**",
                        "/console/**"
                        ,"/api/save/sms","/api/save/User","/api/login/sms",
                        "/api/login/userSms","/api/login/user","/wx/getCode");

        registry
                .addInterceptor(getConsoleHandlerInterceptorConfig())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**","/error","/file/firmware/**","/file/**","/api/**",
                        "/console/login/account","/wx/getCode");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/firmware/**").addResourceLocations("file:"+fileFirmwarePath);
        registry.addResourceHandler("/file/image/**").addResourceLocations("file:"+uediterFilepath);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 设置允许请求方式
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")
                // 是否允许证书（cookies）
                .allowCredentials(true)
                // 预请求的结果能被缓存多久
                .maxAge(3600)
                // 设置允许的请求头
                .allowedHeaders("*");
    }


}
