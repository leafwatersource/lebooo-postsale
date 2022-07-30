package com.api.lebooo.master.api.controller;

import com.api.lebooo.enums.CodeEnum;
import com.api.lebooo.master.service.SmsService;
import com.api.lebooo.master.service.UserService;
import com.api.lebooo.model.User;
import com.api.lebooo.model.UserExample;
import com.api.lebooo.utils.ReferenceUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import me.fishlord.common.result.ErrorCode;
import me.fishlord.common.result.ResultEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Date 2022-05-17 14:54
 */
@Slf4j
@RestController
@RequestMapping(value = "/api")
public class UserApiController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private UserService userService;

    /**
     * 发送注册验证码
     */
    @RequestMapping(value = "/save/sms")
    @ResponseBody
    public ResultEntity getSaveSms(@RequestBody String json) {
        ResultEntity resultEntity = new ResultEntity();
        JsonObject jsonStr = new Gson().fromJson(json, JsonObject.class);
        String account = jsonStr.get("account").getAsString();
        if (StringUtils.isBlank(account)){
            resultEntity.setCode(-1);
            resultEntity.setMsg("输入有误，请检查");
            return resultEntity;
        }
        UserExample userExample = new UserExample();
        userExample.or().andIsDelEqualTo(0).andAccountEqualTo(account);
        List<User> userList = userService.userList(userExample);
        if (userList.size() != 0) {
            resultEntity.setCode(-1);
            resultEntity.setMsg("该手机号码已注册过，请重新输入");
            return resultEntity;
        }
        Pattern p = Pattern.compile(ReferenceUtil.MOBILE_PATTERN);
        Matcher m = p.matcher(account);
        if (m.matches()) {
            resultEntity = smsService.getSmsSave(account);
        } else {
            resultEntity.setCode(CodeEnum.ERROR.getCode());
            resultEntity.setMsg("手机号码有误");
        }
        return resultEntity;
    }

    /**
     * 用户注册
     */
    @RequestMapping(value = "/save/User")
    @ResponseBody
    public ResultEntity saveUser(@RequestBody String json) {
        ResultEntity resultEntity = new ResultEntity();
        JsonObject jsonStr = new Gson().fromJson(json, JsonObject.class);
        log.info("/save/User{},"+jsonStr);
        String account = jsonStr.get("account").getAsString();
        if (StringUtils.isBlank(account)){
            resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
            resultEntity.setMsg("输入有误，请检查");
            return resultEntity;
        }
        String password = jsonStr.get("password").getAsString();
        if (StringUtils.isBlank(password)){
            resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
            resultEntity.setMsg("输入有误，请检查");
            return resultEntity;
        }
        String code = jsonStr.get("code").getAsString();
        if (StringUtils.isBlank(code)){
            resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
            resultEntity.setMsg("输入有误，请检查");
            return resultEntity;
        }

        String openid = null;
        if (jsonStr.has("openid")) {
            openid = jsonStr.get("openid").getAsString();
            if (StringUtils.isBlank(openid)){
                openid = null;
            }
        }
        Pattern p = Pattern.compile(ReferenceUtil.MOBILE_PATTERN);
        Matcher m = p.matcher(account);
        if (m.matches()) {
            resultEntity = userService.saveUser(account,password,code,openid);
        } else {
            resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
            resultEntity.setMsg("手机号码有误");
        }
        log.info("/save/User,resultEntity{},"+resultEntity);
        return resultEntity;
    }

    /**
     * 发送登录验证码
     */
    @RequestMapping(value = "/login/sms")
    @ResponseBody
    public ResultEntity getLoginSms(@RequestBody String json) {
        ResultEntity resultEntity = new ResultEntity();
        JsonObject jsonStr = new Gson().fromJson(json, JsonObject.class);
        String account = jsonStr.get("account").getAsString();
        if (StringUtils.isBlank(account)){
            resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
            resultEntity.setMsg("输入有误，请检查");
            return resultEntity;
        }
        Pattern p = Pattern.compile(ReferenceUtil.MOBILE_PATTERN);
        Matcher m = p.matcher(account);
        if (m.matches()) {
            resultEntity = smsService.getSms(account);
        } else {
            resultEntity.setCode(CodeEnum.ERROR.getCode());
            resultEntity.setMsg("手机号码有误");
        }
        return resultEntity;
    }

    /**
     * 用户验证码登录
     */
    @RequestMapping(value = "/login/userSms")
    @ResponseBody
    public ResultEntity loginUserSms(@RequestBody String json) {
        ResultEntity resultEntity = new ResultEntity();
        JsonObject jsonStr = new Gson().fromJson(json, JsonObject.class);
        log.info("/login/userSms {}"+jsonStr);
        String account = jsonStr.get("account").getAsString();
        if (StringUtils.isBlank(account)){
            resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
            resultEntity.setMsg("输入有误，请检查");
            return resultEntity;
        }
        String code = jsonStr.get("code").getAsString();
        if (StringUtils.isBlank(code)){
            resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
            resultEntity.setMsg("验证码输入有误，请检查");
            return resultEntity;
        }

        String openid = null;
        if (jsonStr.has("openid")) {
            openid = jsonStr.get("openid").getAsString();
            if (StringUtils.isBlank(openid)){
                openid = null;
            }
        }

        Pattern p = Pattern.compile(ReferenceUtil.MOBILE_PATTERN);
        Matcher m = p.matcher(account);
        if (m.matches()) {
            resultEntity = userService.loginUserSms(account,code,openid);
        } else {
            resultEntity.setCode(CodeEnum.ERROR.getCode());
            resultEntity.setMsg("手机号码有误");
        }
        log.info("/login/userSms resultEntity{}"+resultEntity);
        return resultEntity;
    }

    /**
     * 用户密码登录
     */
    @RequestMapping(value = "/login/user")
    @ResponseBody
    public ResultEntity loginUser(@RequestBody String json) {
        ResultEntity resultEntity = new ResultEntity();
        JsonObject jsonStr = new Gson().fromJson(json, JsonObject.class);
        log.info("/login/user {}"+jsonStr);
        String account = jsonStr.get("account").getAsString();
        if (StringUtils.isBlank(account)){
            resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
            resultEntity.setMsg("输入有误，请检查");
            return resultEntity;
        }
        String password = jsonStr.get("password").getAsString();
        if (StringUtils.isBlank(password)){
            resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
            resultEntity.setMsg("输入有误，请检查");
            return resultEntity;
        }
        String openid = null;
        if (jsonStr.has("openid")) {
            openid = jsonStr.get("openid").getAsString();
            if (StringUtils.isBlank(openid)){
                openid = null;
            }
        }

        Pattern p = Pattern.compile(ReferenceUtil.MOBILE_PATTERN);
        Matcher m = p.matcher(account);
        if (m.matches()) {
            resultEntity = userService.loginUser(account,password,openid);
        } else {
            resultEntity.setCode(CodeEnum.ERROR.getCode());
            resultEntity.setMsg("手机号码有误");
        }
        log.info("/login/userSms resultEntity{}"+resultEntity);
        return resultEntity;
    }
}
