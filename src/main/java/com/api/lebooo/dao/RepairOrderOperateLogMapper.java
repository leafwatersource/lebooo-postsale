package com.api.lebooo.dao;

import com.api.lebooo.model.RepairOrderOperateLog;
import com.api.lebooo.model.RepairOrderOperateLogExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface RepairOrderOperateLogMapper extends BaseMapper<RepairOrderOperateLog, RepairOrderOperateLogExample, Long> {
}