package com.api.lebooo.dao;

import com.api.lebooo.model.RepairOrder;
import com.api.lebooo.model.RepairOrderExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface RepairOrderMapper extends BaseMapper<RepairOrder, RepairOrderExample, Long> {
}