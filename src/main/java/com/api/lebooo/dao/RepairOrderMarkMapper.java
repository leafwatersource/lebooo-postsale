package com.api.lebooo.dao;

import com.api.lebooo.model.RepairOrderMark;
import com.api.lebooo.model.RepairOrderMarkExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface RepairOrderMarkMapper extends BaseMapper<RepairOrderMark, RepairOrderMarkExample, Long> {
}