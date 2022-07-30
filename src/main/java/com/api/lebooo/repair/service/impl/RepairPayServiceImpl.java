package com.api.lebooo.repair.service.impl;

import com.api.lebooo.dao.RepairPayMapper;
import com.api.lebooo.model.RepairPay;
import com.api.lebooo.model.RepairPayExample;
import com.api.lebooo.repair.service.RepairPayService;
import me.fishlord.common.dto.BaseResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date 2022-05-21 16:05
 */
@Service
public class RepairPayServiceImpl implements RepairPayService {

    @Autowired
    private RepairPayMapper repairPayMapper;

    @Override
    public BaseResultDTO saveRepairPay(RepairPay repairPay) {
        BaseResultDTO baseResultDTO = new BaseResultDTO();
        repairPayMapper.insertSelective(repairPay);
        baseResultDTO.setSuccess(true);
        return baseResultDTO;
    }

    @Override
    public BaseResultDTO updateRepairPay(RepairPay repairPay) {
        BaseResultDTO baseResultDTO = new BaseResultDTO();
        repairPayMapper.updateByPrimaryKeySelective(repairPay);
        baseResultDTO.setSuccess(true);
        return baseResultDTO;
    }

    @Override
    public List<RepairPay> repairPayList(RepairPayExample repairPayExample) {
        return repairPayMapper.selectByExample(repairPayExample);
    }

    @Override
    public RepairPay repairPay(Long id) {
        return repairPayMapper.selectByPrimaryKey(id);
    }
}
