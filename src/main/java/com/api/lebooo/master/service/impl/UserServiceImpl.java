package com.api.lebooo.master.service.impl;

import com.api.lebooo.dao.UserMapper;
import com.api.lebooo.enums.CodeEnum;
import com.api.lebooo.master.service.UserService;
import com.api.lebooo.model.User;
import com.api.lebooo.model.UserExample;
import com.api.lebooo.utils.JedisPoolUtils;
import com.api.lebooo.utils.ReferenceUtil;
import com.api.lebooo.utils.SmsUtil;
import com.api.lebooo.utils.TokenUtil;
import com.api.lebooo.utils.encryptTools.AESUtils;
import me.fishlord.common.dto.BaseResultDTO;
import me.fishlord.common.result.ResultEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date 2022-05-16 15:44
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JedisPoolUtils jedisPoolUtils;

    @Autowired
    private UserMapper userMapper;


    /**
     * 用户密码登录
     */
    @Override
    public ResultEntity loginUser(String account, String password, String openid) {

        ResultEntity resultEntity = new ResultEntity();
        try {
            UserExample userExample = new UserExample();
            userExample.or().andAccountEqualTo(account).andIsDelEqualTo(0);
            List<User> list = userMapper.selectByExample(userExample);
            if (list.size() == 0) {
                resultEntity.setCode(CodeEnum.SUCCESS_NO_FOUND.getCode());
                resultEntity.setMsg("该账号未注册，请重新输入");
                return resultEntity;
            }

            Map<String,Object> map = new HashMap<>();
            User user = list.get(0);
            if (StringUtils.isBlank(user.getPassword())) {
                resultEntity.setCode(CodeEnum.SUCCESS_NO_FOUND.getCode());
                resultEntity.setMsg("账号或密码错误，请重新输入");
                return resultEntity;
            }
            if (StringUtils.isBlank(user.getSalt())) {
                resultEntity.setCode(CodeEnum.SUCCESS_NO_FOUND.getCode());
                resultEntity.setMsg("账号或密码错误，请重新输入");
                return resultEntity;
            }

            String passw = DigestUtils.md5Hex(password + user.getSalt());
            if (!passw.equals(user.getPassword())) {
                resultEntity.setCode(CodeEnum.SUCCESS_NO_FOUND.getCode());
                resultEntity.setMsg("账号或密码错误，请重新输入");
                return resultEntity;
            }

            if (StringUtils.isNotBlank(user.getToken())) {
                jedisPoolUtils.del(ReferenceUtil.TOKENKEY+user.getToken());
            }
            String token = TokenUtil.getInstance().generateTokenUUID();
            map.put("token", token);
            jedisPoolUtils.setex(ReferenceUtil.TOKENKEY+token, ReferenceUtil.SECONDS, String.valueOf(list.get(0).getId()));
            user.setToken(token);
            if (openid != null){
                user.setWxGzhOpenid(openid);
            }
            user.setUpdateTime(new Date());
            userMapper.updateByPrimaryKeySelective(user);

            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
            resultEntity.setMsg("登录成功");
            resultEntity.setData(map);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return resultEntity;
    }

    /**
     * 验证码登录
     */
    @Override
    public ResultEntity loginUserSms(String account, String code, String openid) {

        ResultEntity resultEntity = new ResultEntity();
        try {
            String accountKey = jedisPoolUtils.get(ReferenceUtil.KEY+account);
            if (StringUtils.isBlank(accountKey)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("验证码失效了");
                return resultEntity;
            }
            if (!code.equals(accountKey)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("验证码输入有误，请检查");
                return resultEntity;
            }

            UserExample userExample = new UserExample();
            userExample.or().andAccountEqualTo(account).andIsDelEqualTo(0);
            List<User> list = userMapper.selectByExample(userExample);
            Map<String,Object> map = new HashMap<>();
            String token = TokenUtil.getInstance().generateTokenUUID();
            Date date = new Date();
            if (list.size() == 0) {//无账号时，创建新账号
                User user = new User();
                user.setAccount(account);
                String salt= AESUtils.generateSalt();
                user.setPassword(DigestUtils.md5Hex(DigestUtils.md5Hex(account.substring(account.length()-6)) + salt));//密码默认手机号后六位
                user.setSalt(salt);
                user.setToken(token);
                if (openid != null){
                    user.setWxGzhOpenid(openid);
                }
                user.setUpdateTime(date);
                user.setCreateTime(date);
                userMapper.insertSelective(user);

                jedisPoolUtils.del(accountKey);
                //注册成功，短信通知
                SmsUtil.getSmsPara("1192335",account,"手机号后6位");
                map.put("token", token);
                jedisPoolUtils.setex(ReferenceUtil.TOKENKEY+token, ReferenceUtil.SECONDS, String.valueOf(user.getId()));
                resultEntity.setCode(CodeEnum.SUCCESS.getCode());
                resultEntity.setMsg("登录成功");
                resultEntity.setData(map);
                return resultEntity;
            }

            User user = list.get(0);
            if (StringUtils.isNotBlank(user.getToken())) {
                jedisPoolUtils.del(ReferenceUtil.TOKENKEY+user.getToken());
            }
            user.setToken(token);
            if (openid != null){
                user.setWxGzhOpenid(openid);
            }
            user.setUpdateTime(date);
            userMapper.updateByPrimaryKeySelective(user);
            jedisPoolUtils.del(accountKey);
            jedisPoolUtils.setex(ReferenceUtil.TOKENKEY+token, ReferenceUtil.SECONDS, String.valueOf(list.get(0).getId()));


            map.put("token", token);
            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
            resultEntity.setMsg("登录成功");
            resultEntity.setData(map);
        }catch (Exception e) {
            e.printStackTrace();
            resultEntity.setCode(CodeEnum.ERROR.getCode());
        }
        return resultEntity;
    }

    /**
     * 用户注册
     * @param account
     * @param password
     * @param code
     * @return
     */
    @Override
    public ResultEntity saveUser(String account, String password, String code, String openid) {
        ResultEntity resultEntity = new ResultEntity();
        try {
            String accountKey = jedisPoolUtils.get(ReferenceUtil.KEYS+account);
            if (StringUtils.isBlank(accountKey)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("验证码失效了");
                return resultEntity;
            }
            if (!code.equals(accountKey)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NO_FOUND.getCode());
                resultEntity.setMsg("验证码输入错误，请重新输入");
                return resultEntity;
            }

            UserExample userExample = new UserExample();
            userExample.or().andAccountEqualTo(account).andIsDelEqualTo(0);
            List<User> list = userMapper.selectByExample(userExample);
            Map<String,Object> map = new HashMap<>();
            if (list.size() != 0) {
                resultEntity.setCode(CodeEnum.SUCCESS_NO_FOUND.getCode());
                resultEntity.setMsg("用户已存在");
                return resultEntity;
            }

            String token = TokenUtil.getInstance().generateTokenUUID();
            Date date = new Date();
            User user = new User();
            user.setAccount(account);
            String salt= AESUtils.generateSalt();
            user.setPassword(DigestUtils.md5Hex(password + salt));
            user.setSalt(salt);
            user.setToken(token);
            if (openid != null) {
                user.setWxGzhOpenid(openid);
            }
            user.setUpdateTime(date);
            user.setCreateTime(date);
            userMapper.insertSelective(user);

            jedisPoolUtils.del(accountKey);
            map.put("token", token);
            jedisPoolUtils.setex(ReferenceUtil.TOKENKEY+token, ReferenceUtil.SECONDS, String.valueOf(user.getId()));
            jedisPoolUtils.del(ReferenceUtil.KEYS+account);
            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
            resultEntity.setMsg("登录成功");
            resultEntity.setData(map);

            //注册成功，短信通知
            SmsUtil.getSmsPara("1192331",account,"");
        }catch (Exception e) {
            e.printStackTrace();
            resultEntity.setCode(CodeEnum.ERROR.getCode());
        }
        return resultEntity;
    }

    @Override
    public User getUser(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<User> userList(UserExample userExample) {
        return userMapper.selectByExample(userExample);
    }

    @Override
    public BaseResultDTO updateUser(User user) {
        BaseResultDTO baseResultDTO = new BaseResultDTO();
        userMapper.updateByPrimaryKeySelective(user);
        baseResultDTO.setSuccess(true);
        return baseResultDTO;
    }


}
