package com.api.lebooo.repair.console.controller;

import com.api.lebooo.enums.CodeEnum;
import com.api.lebooo.model.*;
import com.api.lebooo.repair.service.*;
import com.api.lebooo.utils.DateUtil;
import com.api.lebooo.utils.JedisPoolUtils;
import com.api.lebooo.utils.ReferenceUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import me.fishlord.common.result.PageResultEntity;
import me.fishlord.common.result.ResultEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @Date 2022-06-07 10:52
 */
@Controller
@RequestMapping(value = "/console")
public class RepairOrderConsoleController {

    @Autowired
    private JedisPoolUtils jedisPoolUtils;

    @Autowired
    private RepairOrderService repairOrderService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private RepairOrderSequenceService repairOrderSequenceService;

    @Autowired
    private RepairPayService repairPayService;

    @Autowired
    private RepairOrderOperateLogService repairOrderOperateLogService;

    /**
     *报修订单列表
     */
    @RequestMapping(value = "/repairOrderConsole/list")
    @ResponseBody
    public PageResultEntity orderList(@RequestAttribute String json) {

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

            RepairOrderExample repairOrderExample = new RepairOrderExample();
            RepairOrderExample.Criteria criteria = repairOrderExample.or();

            //0 待审核，1 待寄回， 2 已寄回，3 已完成，4 拒绝，5 取消， 6 超时，10 待分配
            if (jsonStr.has("type")){
                String type = jsonStr.get("type").getAsString();
                if ("0".equals(type)){
                    criteria.andOrderStatusEqualTo(0);
                }else if ("1".equals(type)){
                    criteria.andOrderStatusEqualTo(1);
                }else if ("2".equals(type)){
                    criteria.andOrderStatusEqualTo(2);
                }else if ("3".equals(type)){
                    criteria.andOrderStatusEqualTo(3);
                }else if ("4".equals(type) || "5".equals(type) || "6".equals(type)){
                    RepairOrderExample.Criteria criteria2 = repairOrderExample.createCriteria();
                    RepairOrderExample.Criteria criteria3 = repairOrderExample.createCriteria();
                    criteria.andOrderStatusEqualTo(4).andIsDelEqualTo(0);
                    criteria2.andOrderStatusEqualTo(5).andIsDelEqualTo(0);
                    criteria3.andOrderStatusEqualTo(6).andIsDelEqualTo(0);
                    repairOrderExample.or(criteria2);
                    repairOrderExample.or(criteria3);
                }else if ("10".equals(type)){
                    criteria.andAccountIdIsNull().andIsDelEqualTo(0);
                }
            }

            if (jsonStr.has("ordernumber")) {
                RepairOrderExample.Criteria criteria4 = repairOrderExample.createCriteria();
                String ordernumber = jsonStr.get("ordernumber").getAsString();
                criteria4.andOrdernumberEqualTo(ordernumber).andIsDelEqualTo(0);
                repairOrderExample.or(criteria4);

            }else if (jsonStr.has("username")) {
                RepairOrderExample.Criteria criteria5 = repairOrderExample.createCriteria();
                String username = jsonStr.get("username").getAsString();
                criteria5.andUsernameEqualTo(username).andIsDelEqualTo(0);
                repairOrderExample.or(criteria5);

            }else if (jsonStr.has("telephone")) {
                RepairOrderExample.Criteria criteria6 = repairOrderExample.createCriteria();
                String telephone = jsonStr.get("telephone").getAsString();
                criteria6.andTelephoneEqualTo(telephone).andIsDelEqualTo(0);
                repairOrderExample.or(criteria6);

            }else if (jsonStr.has("repairExpressNumber")) {
                RepairOrderExample.Criteria criteria7 = repairOrderExample.createCriteria();
                String repairExpressNumber = jsonStr.get("repairExpressNumber").getAsString();
                criteria7.andRepairExpressNumberEqualTo(repairExpressNumber).andIsDelEqualTo(0);
                repairOrderExample.or(criteria7);

            }else if (jsonStr.has("startTime") && jsonStr.has("endTime")) {
                RepairOrderExample.Criteria criteria8 = repairOrderExample.createCriteria();
                String startTime = jsonStr.get("startTime").getAsString();//yyyy-MM-dd
                String endTime = jsonStr.get("endTime").getAsString();//yyyy-MM-dd
                DateUtil.stringToDate(startTime+" 00:00:00");
                DateUtil.stringToDate(endTime+" 23:59:59");
                criteria8.andCreateTimeBetween(DateUtil.stringToDate(startTime+" 00:00:00"),DateUtil.stringToDate(endTime+" 23:59:59")).andIsDelEqualTo(0);
                repairOrderExample.or(criteria8);
            }

            repairOrderExample.setIsPage(1);
            repairOrderExample.setPageNo(Integer.parseInt(pageNo));
            repairOrderExample.setPageSize(Integer.parseInt(pageSize));
            repairOrderExample.setOrderByClause("create_time desc");

            int count = repairOrderService.orderCount(repairOrderExample);
            pageResultEntity.setTotal(count);
            List<Map<String,Object>> mapList;
            if (count == 0) {
                mapList = Collections.emptyList();
                pageResultEntity.setRows(mapList);
                pageResultEntity.setCode(CodeEnum.SUCCESS.getCode());
                return pageResultEntity;
            }

            DeviceExample deviceExample = new DeviceExample();
            deviceExample.setDistinct(true);
            deviceExample.or().andIsDelEqualTo(0);
            List<Device> deviceList = deviceService.deviceList(deviceExample);

            List<RepairOrder> list = repairOrderService.orderList(repairOrderExample);
            mapList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                RepairOrder repairOrder = list.get(i);
                Map<String,Object> map = new HashMap<>();
                map.put("repairOrderId",repairOrder.getId());
                map.put("createTime",DateUtil.toStringO(repairOrder.getCreateTime()));
                map.put("ordernumber",repairOrder.getOrdernumber());
                map.put("username",repairOrder.getUsername());
                map.put("telephone",repairOrder.getTelephone());
                String deviceName = "";
                if (deviceList.size() != 0) {
                    for (Device device : deviceList) {
                        if (device.getBroadcaster().equals(repairOrder.getBroadcaster())) {
                            deviceName = device.getDeviceName();
                            break;
                        }
                    }
                }
                map.put("deviceName",deviceName);
                map.put("guaranteeStatus",repairOrder.getGuaranteeStatus());
                map.put("refundMoney",repairOrder.getRefundMoney());
                int orderStatus = repairOrder.getOrderStatus();
                if (jsonStr.has("type")) {
                    String type = jsonStr.get("type").getAsString();
                    if ("10".equals(type)) {
                        orderStatus = 10;//待分配
                    }
                }
                map.put("orderStatus",orderStatus);
                map.put("broadcaster",repairOrder.getBroadcaster());
                map.put("color",repairOrder.getColor());
                map.put("takeArea",repairOrder.getTakeArea());
                map.put("takeAddress",repairOrder.getTakeAddress());

                RepairOrderSequence repairOrderSequence = repairOrderSequenceService.getSequenceByRepairOrderId(repairOrder.getId());
                if (repairOrderSequence != null) {
                    map.put("markIsStatus",repairOrderSequence.getIsMark());
                }else {
                    map.put("markIsStatus","");
                }
                mapList.add(map);
            }

            pageResultEntity.setRows(mapList);
            pageResultEntity.setCode(CodeEnum.SUCCESS.getCode());
        }catch (Exception e) {
            e.printStackTrace();
            pageResultEntity.setCode(CodeEnum.ERROR.getCode());
        }
        return pageResultEntity;
    }

    /**
     * 订单
     * 分配、转交
     * type 0 分配，1 转交
     */
    @RequestMapping(value = "/repairOrderConsole/distribute")
    @ResponseBody
    public ResultEntity distribute(@RequestAttribute String json,@RequestAttribute Account account) {

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
            String accountId = jsonStr.get("accountId").getAsString();
            if (StringUtils.isBlank(accountId)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }

            String type = jsonStr.get("type").getAsString();
            if (StringUtils.isBlank(type)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }

            String[] str = repairOrderId.split(",");
            for (int i = 0; i < str.length; i++) {
                RepairOrder repairOrder = new RepairOrder();
                repairOrder.setId(Long.parseLong(str[i]));
                repairOrder.setAccountId(Long.parseLong(accountId));
                repairOrder.setUpdateTime(new Date());
                repairOrderService.updateRepairOrder(repairOrder);
                repairOrderSequenceService.isTimeRepairOrderSequence(Long.parseLong(str[i]),0,account.getId());

                if ("0".equals(type)) {
                    repairOrderOperateLogService.insertRepairOrderOperateLog(Long.parseLong(str[i]),0,"分配了新工单",account.getAccountName());
                }else {
                    repairOrderOperateLogService.insertRepairOrderOperateLog(Long.parseLong(str[i]),2,"转交了该工单",account.getAccountName());
                }
            }
            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
            return resultEntity;
        } catch (Exception e) {
            resultEntity.setCode(CodeEnum.ERROR.getCode());
            e.printStackTrace();
        }
        resultEntity.setData(null);
        return resultEntity;
    }

    /**
     * 订单
     * 受理
     */
    @RequestMapping(value = "/repairOrderConsole/getDistribute")
    @ResponseBody
    public ResultEntity getDistribute(@RequestAttribute String json, @RequestAttribute Account account) {

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

            RepairOrder repairOrder = new RepairOrder();
            repairOrder.setId(Long.parseLong(repairOrderId));
            repairOrder.setAccountId(account.getId());
            repairOrder.setUpdateTime(new Date());
            repairOrderService.updateRepairOrder(repairOrder);
            repairOrderSequenceService.isTimeRepairOrderSequence(Long.parseLong(repairOrderId),0,account.getId());
            repairOrderOperateLogService.insertRepairOrderOperateLog(Long.parseLong(repairOrderId),1,"受理了新工单工单",account.getAccountName());
            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
            return resultEntity;
        } catch (Exception e) {
            resultEntity.setCode(CodeEnum.ERROR.getCode());
            e.printStackTrace();
        }
        resultEntity.setData(null);
        return resultEntity;
    }

    /**
     * 订单状态操作
     * type 0 同意，1 拒绝，2 完结
     */
    @RequestMapping(value = "/repairOrderConsole/orderStatus")
    @ResponseBody
    public ResultEntity orderStatus(@RequestAttribute String json, @RequestAttribute Account account) {

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
            String orderStatus = jsonStr.get("orderStatus").getAsString();
            if (StringUtils.isBlank(orderStatus)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            String type = jsonStr.get("type").getAsString();
            if (StringUtils.isBlank(type)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }

            String typeLog = "";
            RepairOrder newRepairOrder = new RepairOrder();
            if ("0".equals(orderStatus)){//待审核订单
                if ("0".equals(type)){
                    RepairOrder repairOrder = repairOrderService.getRepairOrderById(Long.parseLong(repairOrderId));
                    if (0 == repairOrder.getPaymentMethod()) {
                        newRepairOrder.setIsPay(0);//可以填写退费账号信息
                    }
                    newRepairOrder.setOrderStatus(1);//待寄回
                    repairOrderSequenceService.isTimeRepairOrderSequence(Long.parseLong(repairOrderId),1,account.getId());
                    typeLog = "3";
                }else if ("1".equals(type)){
                    if (jsonStr.has("remarks")) {
                        String remarks = jsonStr.get("remarks").getAsString();
                        if (StringUtils.isNotBlank(remarks)) {
                            newRepairOrder.setRemarks(remarks);//拒绝订单描述
                        }
                    }
                    newRepairOrder.setOrderStatus(4);//拒绝
                    repairOrderSequenceService.isTimeRepairOrderSequence(Long.parseLong(repairOrderId),4,account.getId());
                    typeLog = "5";
                }
            }else if ("2".equals(orderStatus)){//待寄回订单
                if ("2".equals(type)){
                    newRepairOrder.setOrderStatus(3);//已完成
                    repairOrderSequenceService.isTimeRepairOrderSequence(Long.parseLong(repairOrderId),3,account.getId());
                    typeLog = "4";
                }
            }else {
                resultEntity.setCode(CodeEnum.SUCCESS.getCode());
                resultEntity.setMsg(null);
                return resultEntity;
            }
            newRepairOrder.setId(Long.parseLong(repairOrderId));
            newRepairOrder.setUpdateTime(new Date());
            repairOrderService.updateRepairOrder(newRepairOrder);

            if (StringUtils.isNotBlank(typeLog)){
                String content = "";
                if ("0".equals(typeLog)) {
                    content = "分配了该工单";
                    //添加定时取消任务。当用售后审核同意后显示待寄回，系统超时15天未有记录， 订单关闭，显示超时。
                    jedisPoolUtils.setex(ReferenceUtil.SENDBACKKEY+repairOrderId,ReferenceUtil.SENDBACK_SECONDS,System.currentTimeMillis()+"");
                }else if ("1".equals(typeLog)){
                    content = "受理了该工单";
                }else if ("3".equals(typeLog)){
                    content = "同意了该工单";
                }
                repairOrderOperateLogService.insertRepairOrderOperateLog(Long.parseLong(repairOrderId),Integer.parseInt(typeLog),content,account.getAccountName());
            }

            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
            return resultEntity;
        } catch (Exception e) {
            e.printStackTrace();
            resultEntity.setCode(CodeEnum.ERROR.getCode());
        }
        resultEntity.setData(null);
        return resultEntity;
    }

    /**
     * 订单详情
     * @param json
     * @return
     */
    @RequestMapping(value = "/repairOrderConsole/get")
    @ResponseBody
    public ResultEntity getOrder(@RequestAttribute String json) {

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

            RepairOrder repairOrder = repairOrderService.getRepairOrderById(Long.parseLong(repairOrderId));
            if (repairOrder == null) {
                resultEntity.setCode(CodeEnum.SUCCESS.getCode());
                resultEntity.setData(null);
                return resultEntity;
            }
            DeviceExample deviceExample = new DeviceExample();
            deviceExample.setDistinct(true);
            deviceExample.or().andIsDelEqualTo(0);
            List<Device> deviceList = deviceService.deviceList(deviceExample);

            Map<String,Object> map = new HashMap<>();
            map.put("repairOrderId", repairOrder.getId());
            map.put("username", repairOrder.getUsername());
            map.put("telephone", repairOrder.getTelephone());
            String deviceName = "";
            if (deviceList.size() != 0) {
                for (Device device : deviceList) {
                    if (device.getBroadcaster().equals(repairOrder.getBroadcaster())) {
                        deviceName = device.getDeviceName();
                        break;
                    }
                }
            }
            map.put("deviceName",deviceName);
            map.put("broadcaster", repairOrder.getBroadcaster());
            map.put("color", repairOrder.getColor());
            map.put("channel", repairOrder.getChannel());
            map.put("purchaseDate", repairOrder.getPurchaseDate());
            map.put("guaranteeDate", repairOrder.getGuaranteeDate());
            map.put("guaranteeStatus", repairOrder.getGuaranteeStatus());
            map.put("paymentMethod", repairOrder.getPaymentMethod());
            map.put("proofImg", repairOrder.getProofImg());
            map.put("faultPhenomenon", repairOrder.getFaultPhenomenon());
            map.put("faultDescription", repairOrder.getFaultDescription());
            map.put("faultImg", repairOrder.getFaultImg());
            map.put("devicenumber", repairOrder.getDevicenumber());
            map.put("repairExpress", repairOrder.getRepairExpress());
            map.put("repairExpressNumber", repairOrder.getRepairExpressNumber());
            map.put("takeName", repairOrder.getTakeName());
            map.put("takeTelephone", repairOrder.getTakeTelephone());
            map.put("takeArea", repairOrder.getTakeArea());
            map.put("takeAddress", repairOrder.getTakeAddress());
            map.put("remarks", repairOrder.getRemarks());
            map.put("ordernumber", repairOrder.getOrdernumber());
            map.put("isPay", repairOrder.getIsPay());


            if (repairOrder.getIsPay() == 0){
                Map<String,Object> ma = new HashMap<>();
                RepairPayExample repairPayExample = new RepairPayExample();
                repairPayExample.or().andIsDelEqualTo(0).andUserIdEqualTo(repairOrder.getUserId()).andRepairOrderIdEqualTo(repairOrder.getId());
                List<RepairPay> repairPayList = repairPayService.repairPayList(repairPayExample);
                if (repairPayList.size() == 0){
                    map.put("repairPayJson", null);
                }else  if (repairPayList.size() > 1){
                    resultEntity.setCode(CodeEnum.ERROR.getCode());
                    return resultEntity;
                }else {
                    ma.put("repairPayId", repairPayList.get(0).getId());
                    ma.put("refundName", repairPayList.get(0).getRefundName());
                    ma.put("refundType", repairPayList.get(0).getRefundType());
                    ma.put("refundMoney", repairPayList.get(0).getRefundMoney());
                    ma.put("refundAccount", repairPayList.get(0).getRefundAccount());
                    map.put("repairPayJson", ma);
                }
            }

            /**
             * 订单最新状态
             */
            int orderStatus = 0;
            String time = DateUtil.toStringNo(repairOrder.getCreateTime());
            if (repairOrder.getUserId() == null) {//未分配
                orderStatus = 10;
            }else if (repairOrder.getUserId() != null){//已分配
                orderStatus = repairOrder.getOrderStatus();
                RepairOrderSequence repairOrderSequence = repairOrderSequenceService.getSequenceByRepairOrderId(Long.parseLong(repairOrderId));
                if (repairOrderSequence == null) {
                    time = "";
                }else {//order__status  0 待审核，1 待寄回， 2 已寄回，3 已完成，4 拒绝，5 取消， 6 超时
                   if (repairOrder.getOrderStatus() == 1){
                       time = DateUtil.toStringNo(repairOrderSequence.getWaitTime());
                   }else if (repairOrder.getOrderStatus() == 2) {
                       time = DateUtil.toStringNo(repairOrderSequence.getSendTime());
                   }else if (repairOrder.getOrderStatus() == 3) {
                       time = DateUtil.toStringNo(repairOrderSequence.getFinishTime());
                   }else if (repairOrder.getOrderStatus() == 4) {
                       time = DateUtil.toStringNo(repairOrderSequence.getRejectTime());
                   }else if (repairOrder.getOrderStatus() == 5) {
                       time = DateUtil.toStringNo(repairOrderSequence.getCancelTime());
                   }else if (repairOrder.getOrderStatus() == 6) {
                       time = DateUtil.toStringNo(repairOrderSequence.getExceedTime());
                   }else {
                       time = DateUtil.toStringNo(repairOrderSequence.getDistriTime());
                   }
                }
            }
            map.put("time", time);//时间
            map.put("orderStatus", orderStatus);//状态 0 待审核，1 待寄回， 2 已寄回，3 已完成，4 拒绝，5 取消， 6 超时，10 待分配

            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
            resultEntity.setData(map);
            return resultEntity;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            resultEntity.setCode(CodeEnum.ERROR.getCode());
            return resultEntity;
        }
    }

    /**
     * 运费窗口开关
     * 0 开启，1 关闭
     */
    @RequestMapping(value = "/repairOrderConsole/isPay")
    @ResponseBody
    public ResultEntity isPay(@RequestAttribute String json) {
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
            String isPay = jsonStr.get("isPay").getAsString();
            if (StringUtils.isBlank(isPay)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }

            RepairPayExample repairPayExample = new RepairPayExample();
            repairPayExample.or().andIsDelEqualTo(0).andRepairOrderIdEqualTo(Long.parseLong(repairOrderId));
            List<RepairPay> repairPayList = repairPayService.repairPayList(repairPayExample);
            if (repairPayList.size() == 0) {
                resultEntity.setCode(CodeEnum.SUCCESS.getCode());
                resultEntity.setMsg(null);
                return resultEntity;
            }

            for (RepairPay repairPay : repairPayList) {
                RepairPay newRepairPay = new RepairPay();
                newRepairPay.setId(repairPay.getId());
                newRepairPay.setIsDel(1);
                newRepairPay.setUpdateTime(new Date());
                repairPayService.updateRepairPay(newRepairPay);
            }
            resultEntity.setMsg(null);
            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            resultEntity.setCode(CodeEnum.ERROR.getCode());
        }
        return resultEntity;
    }

}
