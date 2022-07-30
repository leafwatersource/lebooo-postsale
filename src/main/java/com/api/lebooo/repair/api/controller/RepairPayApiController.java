package com.api.lebooo.repair.api.controller;

import com.api.lebooo.enums.CodeEnum;
import com.api.lebooo.model.RepairOrder;
import com.api.lebooo.model.RepairPay;
import com.api.lebooo.model.RepairPayExample;
import com.api.lebooo.model.User;
import com.api.lebooo.repair.service.RepairOrderService;
import com.api.lebooo.repair.service.RepairPayService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.fishlord.common.result.ResultEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date 2022-05-21 16:26
 */
@RestController
@RequestMapping(value = "/api")
public class RepairPayApiController {

    @Autowired
    private RepairOrderService repairOrderService;

    @Autowired
    private RepairPayService repairPayService;

    /**
     * 新增售后退费信息
     */
    @RequestMapping(value = "/save/refund")
    @ResponseBody
    public ResultEntity saveRefund(@RequestAttribute String json, @RequestAttribute User user) {
        ResultEntity resultEntity = new ResultEntity();
        try {
            JsonObject jsonStr = new Gson().fromJson(json, JsonObject.class);

            String repairOrderId = jsonStr.get("repairOrderId").getAsString();
            if (StringUtils.isBlank(repairOrderId)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }

            String refundName = jsonStr.get("refundName").getAsString();
            if (StringUtils.isBlank(refundName)){
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            String refundType = jsonStr.get("refundType").getAsString();
            if (StringUtils.isBlank(refundType)){
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }

            String refundMoney = jsonStr.get("refundMoney").getAsString();
            BigDecimal money = new BigDecimal(refundMoney);
            money = money.setScale(2,BigDecimal.ROUND_HALF_UP);
            if (StringUtils.isBlank(refundMoney)){
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            String refundAccount = jsonStr.get("refundAccount").getAsString();
            if (StringUtils.isBlank(refundAccount)){
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }

            RepairOrder repairOrder = repairOrderService.getRepairOrderById(Long.parseLong(repairOrderId));
            if (repairOrder == null) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("无订单记录");
                return resultEntity;
            }

            if (repairOrder.getIsPay() == 1){
                resultEntity.setCode(CodeEnum.SUCCESS_NO_RANGE.getCode());
                resultEntity.setMsg("无填写权限");
                return resultEntity;
            }

            RepairPay repairPay = new RepairPay();
            repairPay.setRefundName(refundName);
            repairPay.setRefundType(refundType);
            repairPay.setRefundMoney(money);
            repairPay.setRefundAccount(refundAccount);
            repairPay.setUserId(user.getId());
            repairPay.setRepairOrderId(Long.parseLong(repairOrderId));
            Date date = new Date();
            repairPay.setUpdateTime(date);

            RepairOrder repairOrd = new RepairOrder();
            repairOrd.setId(repairOrder.getId());
            repairOrd.setRefundMoney(money);
            repairOrd.setUpdateTime(date);

            repairPayService.saveRepairPay(repairPay);
            repairOrderService.updateRepairOrder(repairOrd);
            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
        }catch (Exception e){
            e.printStackTrace();
            resultEntity.setCode(CodeEnum.ERROR.getCode());
        }
        return resultEntity;
    }

    /**
     * 查看信息
     */
    @RequestMapping(value = "/get/repairPayById")
    @ResponseBody
    public ResultEntity getRepairPayById(@RequestAttribute String json) {
        ResultEntity resultEntity = new ResultEntity();
        try {
            JsonObject jsonStr = new Gson().fromJson(json, JsonObject.class);
            String repairOrderId = jsonStr.get("repairOrderId").getAsString();
            if (StringUtils.isBlank(repairOrderId)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            RepairOrder repairOrder = repairOrderService.getRepairOrderById(Long.parseLong(repairOrderId));
            if (repairOrder == null) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("无订单记录");
                return resultEntity;
            }
            Map<String,Object> map = new HashMap<>();
            if (repairOrder.getIsPay() == 1){
                resultEntity.setCode(CodeEnum.SUCCESS.getCode());
                resultEntity.setData(map);
                return resultEntity;
            }

            RepairPayExample repairPayExample = new RepairPayExample();
            repairPayExample.or().andIsDelEqualTo(0).andRepairOrderIdEqualTo(Long.parseLong(repairOrderId));
            List<RepairPay> repairPayList = repairPayService.repairPayList(repairPayExample);
            if (repairPayList.size() == 0){
                resultEntity.setCode(CodeEnum.SUCCESS.getCode());
                resultEntity.setData(map);
                return resultEntity;
            }

            if (repairPayList.size() != 1){
                resultEntity.setCode(CodeEnum.SUCCESS_NO_FOUND.getCode());
                resultEntity.setData(map);
                return resultEntity;
            }

            map.put("refundName",repairPayList.get(0).getRefundName());
            map.put("refundType",repairPayList.get(0).getRefundType());
            map.put("refundMoney",repairPayList.get(0).getRefundMoney());
            map.put("refundAccount",repairPayList.get(0).getRefundAccount());
            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
            resultEntity.setData(map);
        }catch (Exception e) {
            e.printStackTrace();
            resultEntity.setCode(CodeEnum.ERROR.getCode());
        }
        return resultEntity;
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/update/repairPay")
    @ResponseBody
    public ResultEntity updateRepairPay(@RequestAttribute String json) {
        ResultEntity resultEntity = new ResultEntity();
        try {
            JsonObject jsonStr = new Gson().fromJson(json, JsonObject.class);

            String repairPayId = jsonStr.get("repairPayId").getAsString();
            if (StringUtils.isBlank(repairPayId)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }

            RepairPay repairPay = repairPayService.repairPay(Long.parseLong(repairPayId));
            if (repairPay == null){
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }

            RepairPay repair = new  RepairPay();
            if (jsonStr.has("refundName")) {
                String refundName = jsonStr.get("refundName").getAsString();
                if (StringUtils.isNotBlank(refundName)){
                    repair.setRefundName(refundName);
                }
            }

            if (jsonStr.has("refundType")) {
                String refundType = jsonStr.get("refundType").getAsString();
                if (StringUtils.isNotBlank(refundType)){
                    repair.setRefundType(refundType);
                }
            }

            if (jsonStr.has("refundMoney")) {
                String refundMoney = jsonStr.get("refundMoney").getAsString();
                if (StringUtils.isNotBlank(refundMoney)){
                    BigDecimal money = new BigDecimal(refundMoney);
                    money = money.setScale(2,BigDecimal.ROUND_HALF_UP);
                    repair.setRefundMoney(money);
                }
            }

            if (jsonStr.has("refundAccount")) {
                String refundAccount = jsonStr.get("refundAccount").getAsString();
                if (StringUtils.isNotBlank(refundAccount)){
                    repair.setRefundAccount(refundAccount);
                }
            }
            Date date = new Date();
            repair.setUpdateTime(date);
            repair.setId(repairPay.getId());
            repairPayService.updateRepairPay(repair);

            if (jsonStr.has("refundMoney")) {
                String refundMoney = jsonStr.get("refundMoney").getAsString();
                if (StringUtils.isNotBlank(refundMoney)){
                    BigDecimal money = new BigDecimal(refundMoney);
                    money = money.setScale(2,BigDecimal.ROUND_HALF_UP);
                    RepairOrder repairOrd = new RepairOrder();
                    repairOrd.setId(repairPay.getRepairOrderId());
                    repairOrd.setRefundMoney(money);
                    repairOrd.setUpdateTime(date);
                    repairOrderService.updateRepairOrder(repairOrd);
                }
            }
            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
        }catch (Exception e) {
            e.printStackTrace();
            resultEntity.setCode(CodeEnum.ERROR.getCode());
        }
        return resultEntity;
    }


}
