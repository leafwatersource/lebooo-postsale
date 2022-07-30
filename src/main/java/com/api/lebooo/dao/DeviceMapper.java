package com.api.lebooo.dao;

import com.api.lebooo.model.Device;
import com.api.lebooo.model.DeviceExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface DeviceMapper extends BaseMapper<Device, DeviceExample, Long> {
}