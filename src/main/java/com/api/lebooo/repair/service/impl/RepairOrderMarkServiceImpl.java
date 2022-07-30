package com.api.lebooo.repair.service.impl;

import com.api.lebooo.dao.RepairOrderMarkMapper;
import com.api.lebooo.model.RepairOrderMark;
import com.api.lebooo.model.RepairOrderMarkExample;
import com.api.lebooo.repair.service.RepairOrderMarkService;
import me.fishlord.common.dto.BaseResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date 2022-06-09 10:08
 */
@Service
public class RepairOrderMarkServiceImpl implements RepairOrderMarkService {

    @Autowired
    private RepairOrderMarkMapper repairOrderMarkMapper;

    @Override
    public List<RepairOrderMark> listRepairOrderMark(RepairOrderMarkExample repairOrderMarkExample) {
        return repairOrderMarkMapper.selectByExample(repairOrderMarkExample);
    }

    @Override
    public BaseResultDTO saveRepairOrderMark(RepairOrderMark repairOrderMark) {
        BaseResultDTO baseResultDTO = new BaseResultDTO();
        repairOrderMarkMapper.insertSelective(repairOrderMark);
        baseResultDTO.setSuccess(true);
        return baseResultDTO;
    }
}
