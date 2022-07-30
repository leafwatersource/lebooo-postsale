package com.api.lebooo.repair.service.impl;

import com.api.lebooo.dao.DeviceMapper;
import com.api.lebooo.model.Device;
import com.api.lebooo.model.DeviceExample;
import com.api.lebooo.repair.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date 2022-05-25 11:45
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public List<Device> deviceList(DeviceExample deviceExample) {
        return deviceMapper.selectByExample(deviceExample);
    }
}
