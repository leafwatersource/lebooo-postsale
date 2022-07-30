package com.api.lebooo.repair.console.controller;

import com.api.lebooo.enums.CodeEnum;
import com.api.lebooo.model.RepairOrderOperateLog;
import com.api.lebooo.model.RepairOrderOperateLogExample;
import com.api.lebooo.repair.service.RepairOrderOperateLogService;
import com.api.lebooo.utils.DateUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.fishlord.common.result.PageResultEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @Date 2022-06-18 15:48
 */
@Controller
@RequestMapping(value = "/console")
public class RepairOrderOperateLogConsoleController {

    @Autowired
    private RepairOrderOperateLogService repairOrderOperateLogService;

    /**
     *报修订单操作记录列表
     */
    @RequestMapping(value = "/repairOrderOperateLog/list")
    @ResponseBody
    public PageResultEntity operateLogList(@RequestAttribute String json) {

        PageResultEntity pageResultEntity = new PageResultEntity();
        try {
            if (StringUtils.isBlank(json)){
                pageResultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                pageResultEntity.setMsg("数据有误，请检查");
                return pageResultEntity;
            }

            JsonObject jsonStr = new Gson().fromJson(json, JsonObject.class);
            String pageNo = jsonStr.get("pageNo").getAsString();
            if (StringUtils.isBlank(pageNo)) {
                pageResultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                pageResultEntity.setMsg("数据有误，请检查");
                return pageResultEntity;
            }
            String pageSize = jsonStr.get("pageSize").getAsString();
            if (StringUtils.isBlank(pageSize)) {
                pageResultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                pageResultEntity.setMsg("数据有误，请检查");
                return pageResultEntity;
            }
            String repairOrderId = jsonStr.get("repairOrderId").getAsString();
            if (StringUtils.isBlank(repairOrderId)) {
                pageResultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                pageResultEntity.setMsg("数据有误，请检查");
                return pageResultEntity;
            }

            RepairOrderOperateLogExample example = new RepairOrderOperateLogExample();
            example.or().andIsDelEqualTo(0).andRepairOrderIdEqualTo(Long.parseLong(repairOrderId));
            example.setIsPage(1);
            example.setPageNo(Integer.parseInt(pageNo));
            example.setPageSize(Integer.parseInt(pageSize));
            example.setOrderByClause("create_time desc");
            int count = repairOrderOperateLogService.countRepairOrderOperateLog(example);
            List<Map<String,Object>> mapList;
            if (count == 0) {
                mapList = Collections.emptyList();
                pageResultEntity.setRows(mapList);
                pageResultEntity.setCode(CodeEnum.SUCCESS.getCode());
                return pageResultEntity;
            }
            mapList = new ArrayList<>();
            List<RepairOrderOperateLog> operateLogList = repairOrderOperateLogService.listRepairOrderOperateLog(example);
            for (int i = 0; i < operateLogList.size(); i++) {
                RepairOrderOperateLog orderOperateLog = operateLogList.get(i);
                Map<String,Object> map = new HashMap<>();
                map.put("operateName",orderOperateLog.getOperateName());
                map.put("content",orderOperateLog.getContent());
                map.put("createTime", DateUtil.toString(orderOperateLog.getCreateTime()));
                mapList.add(map);
            }
            pageResultEntity.setRows(mapList);
            pageResultEntity.setCode(CodeEnum.SUCCESS.getCode());
            return pageResultEntity;
        } catch (Exception e) {
            e.printStackTrace();
            pageResultEntity.setCode(CodeEnum.ERROR.getCode());
            return pageResultEntity;
        }

    }
}
