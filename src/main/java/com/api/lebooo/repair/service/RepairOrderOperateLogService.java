package com.api.lebooo.repair.service;

import com.api.lebooo.model.RepairOrderOperateLog;
import com.api.lebooo.model.RepairOrderOperateLogExample;

import java.util.List;

/**
 * @Date 2022-06-18 15:43
 */
public interface RepairOrderOperateLogService {

    List<RepairOrderOperateLog> listRepairOrderOperateLog(RepairOrderOperateLogExample repairOrderOperateLogExample);

    int countRepairOrderOperateLog(RepairOrderOperateLogExample repairOrderOperateLogExample);

    void insertRepairOrderOperateLog(Long repairOrderId,Integer isStatus,String content,String operateName);
}
