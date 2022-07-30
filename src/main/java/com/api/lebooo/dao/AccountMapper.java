package com.api.lebooo.dao;

import com.api.lebooo.model.Account;
import com.api.lebooo.model.AccountExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AccountMapper extends BaseMapper<Account, AccountExample, Long> {
}