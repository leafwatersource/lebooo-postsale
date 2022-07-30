package com.api.lebooo.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class JedisPoolUtils {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 获取key的值
     */
    public String get(String key) {
        if (StringUtils.isNotBlank(key)) {
            return stringRedisTemplate.opsForValue().get(key);
        }
        return null;
    }

    /**
     * 给key赋值，并生命周期设置为seconds
     */
    public void setex(String key, int seconds, String value) {
        if (seconds > 0) {
            if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
                stringRedisTemplate.opsForValue().set(key,value,seconds, TimeUnit.SECONDS);
            }
        }
    }

    /**
     * 删除
     * @param key
     */
    public void del(String key) {
        if (StringUtils.isNotBlank(key)) {
            stringRedisTemplate.delete(key);
        }
    }

    public List<String> getKeysByPrefix(String keyPrefix) {
        if (StringUtils.isNotBlank(keyPrefix)) {
            Set<String> keys = stringRedisTemplate.keys(keyPrefix + "*");
            return Arrays.asList(keys.toArray(new String[0]));
        }
        return null;
    }




}