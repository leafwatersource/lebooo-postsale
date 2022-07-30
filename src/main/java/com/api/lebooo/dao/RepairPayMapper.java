package com.api.lebooo.dao;

import com.api.lebooo.model.RepairPay;
import com.api.lebooo.model.RepairPayExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface RepairPayMapper extends BaseMapper<RepairPay, RepairPayExample, Long> {
}