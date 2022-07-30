package com.api.lebooo.dao;

import com.api.lebooo.model.User;
import com.api.lebooo.model.UserExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserMapper extends BaseMapper<User, UserExample, Long> {
}