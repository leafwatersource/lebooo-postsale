package com.api.lebooo.controller;

import com.api.lebooo.utils.JedisPoolUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Date 2022-05-11 13:46
 */
@Slf4j
@Controller
public class TestController {

    @Autowired
    private JedisPoolUtils jedisPoolUtils;

    @RequestMapping("/getTest")
    @ResponseBody
    public void getTest() {
        log.info("===========getTest()");
    }

    @RequestMapping("/getTest/2")
    @ResponseBody
    public void getTest2() {
        log.info("===========getTest()2");
    }

    @RequestMapping("/getTest/3")
    @ResponseBody
    public void ddd (){
//        String key = "psToken-d331ddb5f9e04033a128c18b514edb24";
        String key = "psToken-";
        String string = jedisPoolUtils.get(key);
        System.out.println("k-v: "+string);
    }
}
