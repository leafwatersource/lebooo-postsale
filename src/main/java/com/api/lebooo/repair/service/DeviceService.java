package com.api.lebooo.repair.service;

import com.api.lebooo.model.Device;
import com.api.lebooo.model.DeviceExample;

import java.util.List;

/**
 * @Date 2022-05-25 11:45
 */
public interface DeviceService {

    List<Device> deviceList(DeviceExample deviceExample);
}
