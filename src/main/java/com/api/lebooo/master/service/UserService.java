package com.api.lebooo.master.service;

import com.api.lebooo.model.User;
import com.api.lebooo.model.UserExample;
import me.fishlord.common.dto.BaseResultDTO;
import me.fishlord.common.result.ResultEntity;

import java.util.List;

/**
 * @Date 2022-05-16 15:44
 */
public interface UserService {

    ResultEntity loginUser(String account, String password, String openid);

    ResultEntity loginUserSms(String account, String code, String openid);

    ResultEntity saveUser(String account, String password, String code, String openid);

    User getUser(Long userId);

    List<User> userList(UserExample userExample);

    BaseResultDTO updateUser(User user);

}
