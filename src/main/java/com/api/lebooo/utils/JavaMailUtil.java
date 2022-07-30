package com.api.lebooo.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class JavaMailUtil {

   /* private String from = "linjinzhao@lebondsonic.com";
    private String password = "test@123";
    private String host = "smtp.mxhichina.com";
    private int millis = 3000;
    private int seconds = 1800;

    @Autowired
    JavaMailSender sender;

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private static HttpServletRequest request;


    @Bean
    public JavaMailSenderImpl JavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setUsername(from);
        mailSender.setPassword(password);
        return  mailSender;
    }

    *//**
     * 发送一般文本邮件
     * @param content
     *//*
    @Async
    public void sendEmailUtil(int subjectTept,String content){

        Jedis jedis = null;
        String subject = "";
        boolean flag = false;

        try {
            Thread.sleep(millis);
            long time = System.currentTimeMillis();
            jedis = jedisPool.getResource();

            switch(subjectTept){
                case 1:
                    String val = jedis.get("sendEmail_1");
                    if (StringUtils.isBlank(val)){
                        flag = true;
                        subject = "固件升级";
                        jedis.setex("sendEmail_1",seconds,time+"");
                    }
                    break;
                case 2:
                    String val2 = jedis.get("sendEmail_2");
                    if (StringUtils.isBlank(val2)){
                        flag = true;
                        subject = "成人刷牙";
                        jedis.setex("sendEmail_2",seconds,time+"");
                    }
                    break;
                case 3:
                    String val3 = jedis.get("sendEmail_3");
                    if (StringUtils.isBlank(val3)){
                        flag = true;
                        subject = "儿童刷牙";
                        jedis.setex("sendEmail_3",seconds,time+"");
                    }
                    break;
                case 4:
                    String val4 = jedis.get("sendEmail_4");
                    if (StringUtils.isBlank(val4)){
                        flag = true;
                        subject = "定时任务-成人刷牙存储";
                        jedis.setex("sendEmail_4",seconds,time+"");
                    }
                    break;
                case 5:
                    String val5 = jedis.get("sendEmail_5");
                    if (StringUtils.isBlank(val5)){
                        flag = true;
                        subject = "成人刷牙存储16个面";
                        jedis.setex("sendEmail_5",seconds,time+"");
                    }
                    break;
                case 6:
                    String val6 = jedis.get("sendEmail_6");
                    if (StringUtils.isBlank(val6)){
                        flag = true;
                        subject = "儿童刷牙存储";
                        jedis.setex("sendEmail_6",seconds,time+"");
                    }
                    break;
                case 7:
                    String val7 = jedis.get("sendEmail_7");
                    if (StringUtils.isBlank(val7)){
                        flag = true;
                        subject = "冲牙器记录";
                        jedis.setex("sendEmail_7",seconds,time+"");
                    }
                    break;
                default:
                    flag = false;

            }
            if (flag){
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(from);
                message.setTo(from);
                message.setSubject(subject);
                message.setText(SpringContextUtil.getActiveProfile()+":"+content);
                message.setSentDate(new Date());
                sender.send(message);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
    }*/

    public static void main(String[] args) {
//        System.out.println("--------------"+SpringContextUtil.getActiveProfile());
    }
}
