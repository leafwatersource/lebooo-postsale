package com.api.lebooo.config;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created Time 18/9/14 下午4:02
 *
 * @author liwang
 */
@ControllerAdvice(basePackages = {"com.api.lebooo.controller","com.api.lebooo.master.console.controller","com.api.lebooo.master.api.controller",
"com.api.lebooo.repair.api.controller","com.api.lebooo.repair.console.controller"})
public class ResponseBodyAdviceConfig implements ResponseBodyAdvice<Object> {

    private final Logger logger = LoggerFactory.getLogger(ResponseBodyAdviceConfig.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        logger.info("url:{},返回数据：{}", serverHttpRequest.getURI(), new Gson().toJson(o));
        return o;
    }
}
