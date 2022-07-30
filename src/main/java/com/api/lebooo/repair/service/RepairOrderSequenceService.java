package com.api.lebooo.repair.service;

import com.api.lebooo.model.RepairOrderSequence;
import me.fishlord.common.dto.BaseResultDTO;
import me.fishlord.common.result.BaseResultEntity;

/**
 * @Date 2022-06-13 14:10
 */
public interface RepairOrderSequenceService {

    void isMarkRepairOrderSequence(Long repairOrderId,int isMark,Long accountId);

    RepairOrderSequence getSequenceByRepairOrderId(Long repairOrderId);

    void isTimeRepairOrderSequence(Long repairOrderId, Integer orderStatus, Long accountId);

    BaseResultEntity updateRepairOrderSequence(RepairOrderSequence repairOrderSequence);
}
