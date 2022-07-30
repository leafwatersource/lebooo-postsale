package com.api.lebooo.repair.service.impl;

import com.api.lebooo.dao.RepairOrderOperateLogMapper;
import com.api.lebooo.model.RepairOrderOperateLog;
import com.api.lebooo.model.RepairOrderOperateLogExample;
import com.api.lebooo.repair.service.RepairOrderOperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Date 2022-06-18 15:43
 */
@Service
public class RepairOrderOperateLogServiceImpl implements RepairOrderOperateLogService {

    @Autowired
    private RepairOrderOperateLogMapper repairOrderOperateLogMapper;

    @Override
    public List<RepairOrderOperateLog> listRepairOrderOperateLog(RepairOrderOperateLogExample repairOrderOperateLogExample) {
        return repairOrderOperateLogMapper.selectByExample(repairOrderOperateLogExample);
    }

    @Override
    public int countRepairOrderOperateLog(RepairOrderOperateLogExample repairOrderOperateLogExample) {
        return repairOrderOperateLogMapper.countByExample(repairOrderOperateLogExample);
    }

    @Override
    public void insertRepairOrderOperateLog(Long repairOrderId,Integer isStatus,String content,String operateName) {
        RepairOrderOperateLog repairOrderOperateLog = new RepairOrderOperateLog();
        repairOrderOperateLog.setRepairOrderId(repairOrderId);
        repairOrderOperateLog.setOperateName(operateName);
        repairOrderOperateLog.setIsStatus(isStatus);
        repairOrderOperateLog.setContent(content);
        repairOrderOperateLog.setCreateTime(new Date());
        repairOrderOperateLogMapper.insertSelective(repairOrderOperateLog);
    }
}
