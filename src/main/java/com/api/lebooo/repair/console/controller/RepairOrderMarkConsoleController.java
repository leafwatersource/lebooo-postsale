package com.api.lebooo.repair.console.controller;

import com.api.lebooo.enums.CodeEnum;
import com.api.lebooo.model.Account;
import com.api.lebooo.model.RepairOrderMark;
import com.api.lebooo.model.RepairOrderMarkExample;
import com.api.lebooo.repair.service.RepairOrderMarkService;
import com.api.lebooo.repair.service.RepairOrderSequenceService;
import com.api.lebooo.utils.DateUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import me.fishlord.common.result.ResultEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @Date 2022-06-09 11:36
 */
@Slf4j
@Controller
@RequestMapping(value = "/console")
public class RepairOrderMarkConsoleController {

    @Autowired
    private RepairOrderMarkService repairOrderMarkService;
    @Autowired
    private RepairOrderSequenceService repairOrderSequenceService;

    /**
     * 订单标记记录列表
     */
    @RequestMapping(value = "/repairOrderMarkConsole/list")
    @ResponseBody
    public ResultEntity list(@RequestAttribute String json){
        ResultEntity resultEntity = new ResultEntity();
        try {
            if (StringUtils.isBlank(json)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            JsonObject jsonStr = new Gson().fromJson(json, JsonObject.class);
            String repairOrderId = jsonStr.get("repairOrderId").getAsString();
            if (StringUtils.isBlank(repairOrderId)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            RepairOrderMarkExample repairOrderMarkExample = new RepairOrderMarkExample();
            repairOrderMarkExample.setOrderByClause("create_time desc");
            repairOrderMarkExample.or().andIsDelEqualTo(0).andRepairOrderIdEqualTo(Long.parseLong(repairOrderId));
            List<RepairOrderMark> repairOrderMarkList = repairOrderMarkService.listRepairOrderMark(repairOrderMarkExample);
            List<Map<String,Object>> mapList = new ArrayList<>();
            if (repairOrderMarkList.size() == 0){
                repairOrderMarkList = Collections.emptyList();
                resultEntity.setData(repairOrderMarkList);
            }else {
                for (RepairOrderMark repairOrderMark : repairOrderMarkList) {
                    Map<String,Object> map = new HashMap<>();
                    map.put("createTime", DateUtil.toString(repairOrderMark.getCreateTime()));
                    map.put("isStatus", repairOrderMark.getIsStatus());// 0 待客退，1 跟进，2 完结，3 待收地址，4 待给仓库，5 需建单，6 待给快递，7 物流停发
                    map.put("img", repairOrderMark.getImg());
                    map.put("content", repairOrderMark.getContent());
                    mapList.add(map);
                }
                resultEntity.setData(mapList);
            }
            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
            return resultEntity;
        } catch (Exception e) {
            resultEntity.setCode(CodeEnum.ERROR.getCode());
            e.printStackTrace();
        }
        resultEntity.setMsg(null);
        return resultEntity;
    }

    /**
     * 新增订单标记记录
     */
    @RequestMapping(value = "/repairOrderMarkConsole/save")
    @ResponseBody
    public ResultEntity save(@RequestAttribute String json, @RequestAttribute Account account){
        ResultEntity resultEntity = new ResultEntity();
        try {
            if (StringUtils.isBlank(json)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            JsonObject jsonStr = new Gson().fromJson(json, JsonObject.class);
            String repairOrderId = jsonStr.get("repairOrderId").getAsString();
            if (StringUtils.isBlank(repairOrderId)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            String content = null;
            if (jsonStr.has("img")) {
                if (StringUtils.isNotBlank(jsonStr.get("content").getAsString())) {
                    content = jsonStr.get("content").getAsString();
                }
            }

            String img = null;
            if (jsonStr.has("img")) {
                if (StringUtils.isNotBlank(jsonStr.get("img").getAsString())) {
                    img = jsonStr.get("img").getAsString();
                }
            }
            //0 待客退，1 跟进，2 完结，3 待收地址，4 待给仓库，5 需建单，6 待给快递，7 物流停发
            String isStatus = jsonStr.get("isStatus").getAsString();
            if (StringUtils.isBlank(isStatus)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }

            RepairOrderMark repairOrderMark = new RepairOrderMark();
            repairOrderMark.setAccountId(account.getId());
            repairOrderMark.setRepairOrderId(Long.parseLong(repairOrderId));
            repairOrderMark.setContent(content);
            repairOrderMark.setImg(img);
            repairOrderMark.setIsStatus(Integer.valueOf(isStatus));
            repairOrderMark.setCreateTime(new Date());
            repairOrderMarkService.saveRepairOrderMark(repairOrderMark);

            repairOrderSequenceService.isMarkRepairOrderSequence(Long.parseLong(repairOrderId),Integer.valueOf(isStatus),account.getId());

            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
            return resultEntity;
        } catch (Exception e) {
            resultEntity.setCode(CodeEnum.ERROR.getCode());
            e.printStackTrace();
        }
        resultEntity.setMsg(null);
        return resultEntity;
    }

}
