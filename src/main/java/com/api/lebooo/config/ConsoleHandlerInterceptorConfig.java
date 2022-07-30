package com.api.lebooo.config;

import com.api.lebooo.enums.CodeEnum;
import com.api.lebooo.master.service.AccountService;
import com.api.lebooo.model.Account;
import com.api.lebooo.utils.GetHostAnalysisUtils;
import com.api.lebooo.utils.JedisPoolUtils;
import com.api.lebooo.utils.ReferenceUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mzlion.core.http.IPUtils;
import lombok.extern.slf4j.Slf4j;
import me.fishlord.common.result.BaseResultEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Date 2022-05-13 9:40
 */
@Slf4j
@Component
public class ConsoleHandlerInterceptorConfig implements HandlerInterceptor {

    @Autowired
    private JedisPoolUtils jedisPoolUtils;

    @Autowired
    private AccountService accountService;

    /**
     * 进入controller层之前拦截请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
            // 在进入这个拦截器之前，对跨域提供支持
            if (responseCors(response, request)) {
                return false;
            }

            String token = request.getHeader("accessToken");
            String json = GetHostAnalysisUtils.getRequestJsonString(request);
            log.info("ip=" + IPUtils.getClientIp(request) + ",url=" + request.getRequestURI() + ",token="+ token + ",json=" + json + ",User-Agent=" + request.getHeader("User-Agent"));

            BaseResultEntity baseResultEntity = new BaseResultEntity();
           /* if (StringUtils.isBlank(token)) {
                baseResultEntity.setCode(CodeEnum.SUCCESS_NOT_LOGIN.getCode());
                baseResultEntity.setMsg("");
                log.error("缺少token:" + request.getRequestURI());
                ObjectMapper objectMapper = new ObjectMapper();
                response.getWriter().write(objectMapper.writeValueAsString(baseResultEntity));
                return false;
            }

            String accountX = jedisPoolUtils.get(ReferenceUtil.TOKENKEYPC+token);
            if (null == accountX) {
                baseResultEntity.setCode(CodeEnum.SUCCESS_NOT_LOGIN.getCode());
                baseResultEntity.setMsg("请重新登录");
                ObjectMapper objectMapper = new ObjectMapper();
                response.getWriter().write(objectMapper.writeValueAsString(baseResultEntity));
                return false;
            }
            Account account = accountService.getAccount(Long.parseLong(accountX));*/
            Account account = accountService.getAccount(1L);
            if (account == null) {
                baseResultEntity.setCode(CodeEnum.SUCCESS_NOT_LOGIN.getCode());
                baseResultEntity.setMsg("用户未登录");
                ObjectMapper objectMapper = new ObjectMapper();
                response.getWriter().write(objectMapper.writeValueAsString(baseResultEntity));
                return false;
            }
            request.setAttribute("account", account);
            request.setAttribute("json", json);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 在进入这个拦截器之前, 对跨域提供支持
     * @param response
     * @param request
     * @return
     */
    private boolean responseCors(HttpServletResponse response, HttpServletRequest request) {
        if (RequestMethod.OPTIONS.name().equals(request.getMethod())) {
            // response.setHeader("Cache-Control","no-cache");
            response.setHeader("Access-control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
            response.setHeader("Access-Control-Allow-Headers", "*");
            // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
            response.setStatus(HttpStatus.OK.value());
            return true;
        }
        return false;
    }
}
