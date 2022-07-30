package com.api.lebooo.repair.service;

import com.api.lebooo.model.RepairOrderMark;
import com.api.lebooo.model.RepairOrderMarkExample;
import me.fishlord.common.dto.BaseResultDTO;
import me.fishlord.common.result.BaseResultEntity;

import java.util.List;

/**
 * @Date 2022-06-09 10:08
 */
public interface RepairOrderMarkService {

    List<RepairOrderMark> listRepairOrderMark(RepairOrderMarkExample repairOrderMarkExample);

    BaseResultDTO saveRepairOrderMark(RepairOrderMark repairOrderMark);
}
