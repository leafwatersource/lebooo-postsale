package com.api.lebooo.dao;

import com.api.lebooo.model.RepairOrderSequence;
import com.api.lebooo.model.RepairOrderSequenceExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface RepairOrderSequenceMapper extends BaseMapper<RepairOrderSequence, RepairOrderSequenceExample, Long> {
}