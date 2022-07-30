package com.api.lebooo.repair.service;

import com.api.lebooo.model.RepairOrder;
import com.api.lebooo.model.RepairOrderExample;
import me.fishlord.common.dto.BaseResultDTO;

import java.util.List;

/**
 * @Date 2022-05-18 15:51
 */
public interface RepairOrderService {

    List<RepairOrder> orderList(RepairOrderExample repairOrderExample);

    int orderCount(RepairOrderExample repairOrderExample);

    BaseResultDTO saveRepairOrder(RepairOrder repairOrder);

    RepairOrder getRepairOrderById(Long id);

    BaseResultDTO updateRepairOrder(RepairOrder repairOrder);
}
