package com.api.lebooo.repair.service.impl;

import com.api.lebooo.dao.RepairOrderMapper;
import com.api.lebooo.model.RepairOrder;
import com.api.lebooo.model.RepairOrderExample;
import com.api.lebooo.repair.service.RepairOrderService;
import lombok.extern.slf4j.Slf4j;
import me.fishlord.common.dto.BaseResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date 2022-05-18 15:51
 */
@Slf4j
@Service
public class RepairOrderServiceImpl implements RepairOrderService {

    @Autowired
    private RepairOrderMapper repairOrderMapper;

    @Override
    public List<RepairOrder> orderList(RepairOrderExample repairOrderExample) {
        return repairOrderMapper.selectByExample(repairOrderExample);
    }

    @Override
    public int orderCount(RepairOrderExample repairOrderExample) {
        return repairOrderMapper.countByExample(repairOrderExample);
    }

    @Override
    public BaseResultDTO saveRepairOrder(RepairOrder repairOrder) {
        BaseResultDTO baseResultDTO = new BaseResultDTO();
        repairOrderMapper.insertSelective(repairOrder);
        baseResultDTO.setSuccess(true);
        return baseResultDTO;
    }

    @Override
    public RepairOrder getRepairOrderById(Long id) {
        return repairOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public BaseResultDTO updateRepairOrder(RepairOrder repairOrder) {
        repairOrderMapper.updateByPrimaryKeySelective(repairOrder);
        BaseResultDTO baseResultDTO = new BaseResultDTO();
        baseResultDTO.setSuccess(true);
        return baseResultDTO;
    }
}
