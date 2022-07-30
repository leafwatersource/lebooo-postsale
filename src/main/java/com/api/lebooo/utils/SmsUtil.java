package com.api.lebooo.utils;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * recNum 手机号
 * extend 验证码
 * @Date 2022-05-17 9:15
 */
@Slf4j
public class SmsUtil {

    /**
     * recNum 手机号
     *
     */
    public static Map<String,Object> getSms(String recNum,String templateId, String[] extend){
        Map<String,Object> map = new HashMap<>();
        //生产环境请求地址：app.cloopen.com
        String serverIp = "app.cloopen.com";
        //请求端口
        String serverPort = "8883";
        //主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
        String accountSId = "aaf98f8947a0321a0147a52c09530253";
        String accountToken = "3ed3e64c7a594e84bfa3e1a7c2187081";
        //请使用管理控制台中已创建应用的APPID
        String appId = "8aaf0708809721d00180d5cf4fea1143";
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSId, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_XML);
        HashMap<String, Object> result = sdk.sendTemplateSMS(recNum,templateId,extend);
        String statusCode = (String) result.get("statusCode");
        if ("000000".equals(statusCode)) {
            map.put("code",0);
            map.put("msg","发送成功");
        }else if ("160040".equals(statusCode)){
            map.put("code",-1);
            map.put("msg","发送次数过多，请次日再试");
        }else if ("160038".equals(statusCode)){
            map.put("code",-1);
            map.put("msg","短信验证码发送过频繁，请稍后再试");
        }else {
            log.info("----------getSms()----------statusCode="+statusCode+",templateId="+templateId+",recNum="+recNum+",extend"+extend);
            map.put("code",-1);
            map.put("msg","短信发送失败，请联系力博得客服");
        }
        return map;
    }


    /**
     * recNum 手机号
     * extend 验证码
     * APPID 管理控制台中已创建的应用
     * templateId 应用模版id
     */
    public static Map<String,Object> getSmsPara(String templateId, String recNum, String extend){
        Map<String,Object> map = new HashMap<>();
        //生产环境请求地址：app.cloopen.com
        String serverIp = "app.cloopen.com";
        //请求端口
        String serverPort = "8883";
        //主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
        String accountSId = "aaf98f8947a0321a0147a52c09530253";
        String accountToken = "3ed3e64c7a594e84bfa3e1a7c2187081";
        //请使用管理控制台中已创建应用的APPID
        String appId = "8aaf0708809721d00180d5cf4fea1143";
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSId, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_XML);
        HashMap<String, Object> result = sdk.sendTemplateSMS(recNum,templateId,new String[]{extend,"30"});
        String statusCode = (String) result.get("statusCode");
        if ("000000".equals(statusCode)) {
            map.put("code",0);
            map.put("msg","发送成功");
        }else if ("160040".equals(statusCode)){
            map.put("code",-1);
            map.put("msg","发送次数过多，请次日再试");
        }else if ("160038".equals(statusCode)){
            map.put("code",-1);
            map.put("msg","短信验证码发送过频繁，请稍后再试");
        }else {
            log.info("----------getSmsPara()----------statusCode="+statusCode+",templateId="+templateId+",recNum="+recNum+",extend"+extend);
            map.put("code",-1);
            map.put("msg","短信发送失败，请联系力博得客服");
        }
        return map;
    }


}
