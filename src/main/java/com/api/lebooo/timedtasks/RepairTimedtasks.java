package com.api.lebooo.timedtasks;

import com.api.lebooo.utils.JedisPoolUtils;
import com.api.lebooo.utils.ReferenceUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * @Date 2022-06-20 13:39
 */
@Slf4j
@Component
public class RepairTimedtasks {

    @Autowired
    private JedisPoolUtils jedisPoolUtils;

//    @Scheduled(cron = "*/5 * * * * ?") // 每5秒
//    @Scheduled(cron = "0  */1  *  *  *") // 每小时
    public void overtimeRepair(){
        try {
            List<String> list = jedisPoolUtils.getKeysByPrefix(ReferenceUtil.SENDBACKKEY);
            if (list.size() > 0){
                for (String key : list) {
                    log.info(""+key);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
