package com.api.lebooo.repair.service;

import com.api.lebooo.model.RepairPay;
import com.api.lebooo.model.RepairPayExample;
import me.fishlord.common.dto.BaseResultDTO;

import java.util.List;

/**
 * @Date 2022-05-21 16:04
 */
public interface RepairPayService {

    BaseResultDTO saveRepairPay(RepairPay repairPay);

    BaseResultDTO updateRepairPay(RepairPay repairPay);

    List<RepairPay> repairPayList(RepairPayExample repairPayExample);

    RepairPay repairPay(Long id);
}
