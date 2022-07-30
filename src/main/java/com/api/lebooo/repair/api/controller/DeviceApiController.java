package com.api.lebooo.repair.api.controller;

import com.api.lebooo.enums.CodeEnum;
import com.api.lebooo.model.Device;
import com.api.lebooo.model.DeviceExample;
import com.api.lebooo.repair.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import me.fishlord.common.result.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Date 2022-05-25 15:49
 */
@Slf4j
@RestController
@RequestMapping(value = "/api")
public class DeviceApiController {

    @Autowired
    private DeviceService deviceService;

    /**
     * 产品型号列表
     * @return
     */
    @RequestMapping(value = "/device/list")
    @ResponseBody
    public ResultEntity deviceList() {
        ResultEntity resultEntity = new ResultEntity();
        try {
            DeviceExample deviceExample = new DeviceExample();
            deviceExample.or().andIsDelEqualTo(0);
            List<Device> list = deviceService.deviceList(deviceExample);
            List<Map<String,Object>> mapList = new ArrayList<>();
            if (list.size() == 0){
                resultEntity.setCode(CodeEnum.SUCCESS.getCode());
                resultEntity.setData(mapList);
            }

            Set<String> set = new HashSet<>();
            for (int j = 0; j < list.size(); j++) {//设备型号去重
                Device device = list.get(j);
                set.add(device.getBroadcaster());
            }

            for (String str : set) {
                Map<String,Object> objectMap = new HashMap<>();
                List<String> listString = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    Device device = list.get(i);
                    if (str.equals(device.getBroadcaster())){
                        listString.add(device.getColor());
                    }
                }
                objectMap.put("broadcaster",str);
                objectMap.put("color",listString);
                mapList.add(objectMap);
            }
            resultEntity.setData(mapList);
            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
        }catch (Exception e){
            e.printStackTrace();
            resultEntity.setCode(CodeEnum.ERROR.getCode());
        }
        return resultEntity;
    }
}
