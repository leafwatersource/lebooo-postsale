package com.api.lebooo.master.service.impl;

import com.api.lebooo.master.service.SmsService;
import com.api.lebooo.utils.JedisPoolUtils;
import com.api.lebooo.utils.ReferenceUtil;
import com.api.lebooo.utils.SmsUtil;
import com.api.lebooo.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import me.fishlord.common.result.ResultEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Date 2022-05-17 9:50
 */
@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private JedisPoolUtils jedisPoolUtils;

    /**
     * 发送登录短信验证码
     */
    @Override
    public ResultEntity getSms(String account) {

        ResultEntity resultEntity = new ResultEntity();
        try {
            if (StringUtils.isNotBlank(jedisPoolUtils.get(ReferenceUtil.KEYCODE60+account))) {//60秒内不重复发验证码
                resultEntity.setCode(0);
                resultEntity.setMsg("短信验证码发送过频繁，请稍后再试");
                return resultEntity;
            }

            String code = String.valueOf(TokenUtil.nextInt());//验证码
            Map<String,Object> ma = SmsUtil.getSmsPara("1192209",account,code);
            Integer result =Integer.valueOf(ma.get("code").toString());
            log.info("result="+result+","+account+","+code);
            String msg =ma.get("msg").toString();
            if (result==0) {
                //验证码写入缓存
                jedisPoolUtils.setex(ReferenceUtil.KEYCODE60+account,ReferenceUtil.KEYCODE60_SECONDS,code);
                jedisPoolUtils.setex(ReferenceUtil.KEY+account,ReferenceUtil.KEY_SECONDS,code);//30分钟
                resultEntity.setCode(0);
                resultEntity.setMsg("短信发送成功");
            } else {
                resultEntity.setCode(result);
                resultEntity.setMsg(msg);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return resultEntity;
    }

    /**
     * 发送用户注册验证码
     */
    @Override
    public ResultEntity getSmsSave(String account) {

        ResultEntity resultEntity = new ResultEntity();
        try {
            if (StringUtils.isNotBlank(jedisPoolUtils.get(ReferenceUtil.KEYCODES60+account))) {//60秒内不重复发验证码
                resultEntity.setCode(0);
                resultEntity.setMsg("短信发送成功");
                return resultEntity;
            }
            String code = String.valueOf(TokenUtil.nextInt());//验证码
            Map<String,Object> ma = SmsUtil.getSmsPara("1192209",account,code);
            Integer result =Integer.valueOf(ma.get("code").toString());
            String msg =ma.get("msg").toString();
            log.info("result="+result+","+account+","+code);
            if (result==0) {
                //验证码写入缓存
                jedisPoolUtils.setex(ReferenceUtil.KEYCODES60+account,ReferenceUtil.KEYCODE60_SECONDS,code);
                jedisPoolUtils.setex(ReferenceUtil.KEYS+account,ReferenceUtil.KEY_SECONDS,code);//30分钟
                resultEntity.setCode(0);
                resultEntity.setMsg("短信发送成功");
            } else {
                resultEntity.setCode(result);
                resultEntity.setMsg(msg);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return resultEntity;
    }
}
