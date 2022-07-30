package com.api.lebooo.repair.service.impl;

import com.api.lebooo.dao.RepairOrderSequenceMapper;
import com.api.lebooo.model.RepairOrderSequence;
import com.api.lebooo.model.RepairOrderSequenceExample;
import com.api.lebooo.repair.service.RepairOrderSequenceService;
import com.api.lebooo.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import me.fishlord.common.dto.BaseResultDTO;
import me.fishlord.common.result.BaseResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Date 2022-06-13 14:10
 */
@Slf4j
@Service
public class RepairOrderSequenceServiceImpl implements RepairOrderSequenceService {

    @Autowired
    private RepairOrderSequenceMapper repairOrderSequenceMapper;

    /**
     * 记录最新标记状态
     */
    @Async("psTaskExecutor")
    @Override
    public void isMarkRepairOrderSequence(Long repairOrderId, int isMark, Long accountId) {
        try {
            Thread.sleep(1000);
            RepairOrderSequenceExample example = new RepairOrderSequenceExample();
            example.or().andIsDelEqualTo(0).andRepairOrderIdEqualTo(repairOrderId);
            List<RepairOrderSequence> repairOrderSequenceList = repairOrderSequenceMapper.selectByExample(example);

            RepairOrderSequence repairOrderSequence = new RepairOrderSequence();
            repairOrderSequence.setAccountId(accountId);
            repairOrderSequence.setIsMark(isMark);
            Date date = new Date();
            repairOrderSequence.setUpdateTime(date);
            if (repairOrderSequenceList.size() == 0){
                repairOrderSequence.setCreateTime(date);
                repairOrderSequenceMapper.insertSelective(repairOrderSequence);
            }else {
                repairOrderSequence.setId(repairOrderSequenceList.get(0).getId());
                repairOrderSequenceMapper.updateByPrimaryKeySelective(repairOrderSequence);
            }
        } catch (Exception e) {
            log.error("Service isMarkRepairOrderSequence");
            e.printStackTrace();
        }
    }

    @Override
    public RepairOrderSequence getSequenceByRepairOrderId(Long repairOrderId) {
        RepairOrderSequenceExample example = new RepairOrderSequenceExample();
        example.or().andIsDelEqualTo(0).andRepairOrderIdEqualTo(repairOrderId);
        List<RepairOrderSequence> repairOrderSequenceList = repairOrderSequenceMapper.selectByExample(example);
        if (repairOrderSequenceList.size() != 0){
            return repairOrderSequenceList.get(0);
        }
        return null;
    }

    /**
     * 保存报修订单状态修改时间
     * @param repairOrderId
     * @param orderStatus 0 分配时间,1 待寄回， 2 已寄回，3 已完成，4 拒绝，5 取消， 6 超时
     */
    @Override
    public void isTimeRepairOrderSequence(Long repairOrderId, Integer orderStatus, Long accountId) {

        try {
            RepairOrderSequenceExample example = new RepairOrderSequenceExample();
            example.or().andIsDelEqualTo(0).andRepairOrderIdEqualTo(repairOrderId);
            List<RepairOrderSequence> repairOrderSequenceList = repairOrderSequenceMapper.selectByExample(example);
            RepairOrderSequence repairOrderSequence = new RepairOrderSequence();
            Date date = new Date();
            if (orderStatus == 0) {
                repairOrderSequence.setDistriTime(date);
            }else if (orderStatus == 1) {
                repairOrderSequence.setWaitTime(date);
            }else if (orderStatus == 2) {
                repairOrderSequence.setSendTime(date);
            }else if (orderStatus == 3) {
                repairOrderSequence.setFinishTime(date);
            }else if (orderStatus == 4) {
                repairOrderSequence.setRejectTime(date);
            }else if (orderStatus == 5) {
                repairOrderSequence.setCancelTime(date);
            }else if (orderStatus == 6) {
                repairOrderSequence.setExceedTime(date);
            }
            repairOrderSequence.setUpdateTime(date);
            repairOrderSequence.setRepairOrderId(repairOrderId);
            if (accountId != null){
                repairOrderSequence.setAccountId(accountId);
            }

            if (repairOrderSequenceList.size() == 0) {
                repairOrderSequence.setCreateTime(date);
                repairOrderSequenceMapper.insertSelective(repairOrderSequence);
            }else {
                if (orderStatus == 0) {
                    repairOrderSequence.setId(repairOrderSequenceList.get(0).getId());
                    repairOrderSequence.setDistriTime(new Date());
                    repairOrderSequenceMapper.updateByPrimaryKeySelective(repairOrderSequence);
                }
            }
        } catch (Exception e) {
            log.error("Service isTimeRepairOrderSequence");
            e.printStackTrace();
        }
    }

    @Override
    public BaseResultEntity updateRepairOrderSequence(RepairOrderSequence repairOrderSequence) {
        BaseResultEntity baseResultEntity = new BaseResultEntity();
        repairOrderSequenceMapper.updateByPrimaryKeySelective(repairOrderSequence);
        return baseResultEntity;
    }
}
