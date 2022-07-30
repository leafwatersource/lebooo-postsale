package com.api.lebooo.master.service;

import me.fishlord.common.result.ResultEntity;

/**
 * @Date 2022-05-17 9:49
 */
public interface SmsService {

    ResultEntity getSms(String account);

    ResultEntity getSmsSave(String account);
}
