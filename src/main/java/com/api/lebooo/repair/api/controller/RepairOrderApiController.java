package com.api.lebooo.repair.api.controller;

import com.api.lebooo.enums.CodeEnum;
import com.api.lebooo.model.*;
import com.api.lebooo.repair.service.*;
import com.api.lebooo.utils.DateUtil;
import com.api.lebooo.utils.SmsUtil;
import com.api.lebooo.utils.TokenUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import me.fishlord.common.result.PageResultEntity;
import me.fishlord.common.result.ResultEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Date 2022-05-18 15:57
 */
@Slf4j
@RestController
@RequestMapping(value = "/api")
public class RepairOrderApiController {

    @Autowired
    private RepairOrderService repairOrderService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private RepairPayService repairPayService;

    @Autowired
    private RepairOrderSequenceService repairOrderSequenceService;

    @Autowired
    private RepairOrderOperateLogService repairOrderOperateLogService;


    /**
     * 售后报修订单
     */
    @RequestMapping(value = "/index/repairOrder")
    @ResponseBody
    public ResultEntity indexRepairOrder(@RequestAttribute User user){

        ResultEntity resultEntity = new ResultEntity();
        try {
            Map<String,Object> map = new HashMap<>();
            RepairOrderExample repairOrderExample = new RepairOrderExample();
            repairOrderExample.or().andIsDelEqualTo(0).andUserIdEqualTo(user.getId());
            List<RepairOrder> orderList = repairOrderService.orderList(repairOrderExample);
            int orderStatus0 = 0;
            int orderStatus1 = 0;
            int orderStatus2 = 0;
            int orderStatus3 = 0;
            int orderStatus4 = 0;
            if (orderList.size() == 0){
                map.put("orderStatus0",orderStatus0);
                map.put("orderStatus1",orderStatus1);
                map.put("orderStatus2",orderStatus2);
                map.put("orderStatus3",orderStatus3);
                map.put("orderStatus4",orderStatus4);
                resultEntity.setCode(CodeEnum.SUCCESS.getCode());
                return resultEntity;
            }

            for (RepairOrder repairOrder : orderList){//order__status
                if (repairOrder.getOrderStatus() == 0){
                    orderStatus0++;
                }else if (repairOrder.getOrderStatus() == 1){
                    orderStatus1++;
                }else if (repairOrder.getOrderStatus() == 2){
                    orderStatus2++;
                }else if (repairOrder.getOrderStatus() == 3){
                    orderStatus3++;
                }else {
                    orderStatus4++;
                }
            }
            map.put("orderStatus0",orderStatus0);
            map.put("orderStatus1",orderStatus1);
            map.put("orderStatus2",orderStatus2);
            map.put("orderStatus3",orderStatus3);
            map.put("orderStatus4",orderStatus4);
            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
            resultEntity.setData(map);
            return resultEntity;
        }catch (Exception e){
            e.printStackTrace();
            resultEntity.setCode(CodeEnum.ERROR.getCode());
        }
        return resultEntity;
    }

    /**
     *报修订单列表
     * @param user
     * @return
     */
    @RequestMapping(value = "/repairOrder/list")
    @ResponseBody
    public PageResultEntity orderList(@RequestAttribute String json, @RequestAttribute User user) {

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
            RepairOrderExample.Criteria criteria = repairOrderExample.or().andUserIdEqualTo(user.getId()).andIsDelEqualTo(0);

            RepairOrderExample.Criteria criteria2 = repairOrderExample.createCriteria();
            RepairOrderExample.Criteria criteria3 = repairOrderExample.createCriteria();

            //0 待审核，1 待寄回， 2 已寄回，3 已完成，4 拒绝，5 取消， 6 超时
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
                    criteria.andOrderStatusEqualTo(4);
                    criteria2.andUserIdEqualTo(user.getId()).andOrderStatusEqualTo(5).andIsDelEqualTo(0);
                    criteria3.andUserIdEqualTo(user.getId()).andOrderStatusEqualTo(6).andIsDelEqualTo(0);
                    repairOrderExample.or(criteria2);
                    repairOrderExample.or(criteria3);
                }
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
            } else {
                List<RepairOrder> list = repairOrderService.orderList(repairOrderExample);
                mapList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    RepairOrder repairOrder = list.get(i);
                    Map<String,Object> map = new HashMap<>();
                    map.put("repairOrderId",repairOrder.getId());
                    map.put("broadcaster",repairOrder.getBroadcaster());
                    map.put("color",repairOrder.getColor());
                    map.put("orderStatus",repairOrder.getOrderStatus());
                    map.put("ordernumber",repairOrder.getOrdernumber());
                    map.put("devicenumber",repairOrder.getDevicenumber());
                    map.put("takeName",repairOrder.getTakeName());
                    map.put("takeTelephone",repairOrder.getTakeTelephone());
                    map.put("takeAddress",repairOrder.getTakeAddress());
                    map.put("guaranteeStatus",repairOrder.getGuaranteeStatus());
                    map.put("repairExpressNumber",repairOrder.getRepairExpressNumber());
                    String img = "";
                    String deviceName = "";
                    DeviceExample deviceExample = new DeviceExample();
                    deviceExample.or().andIsDelEqualTo(0).andDeviceNameEqualTo(repairOrder.getBroadcaster()).andColorEqualTo(repairOrder.getColor());
                    List<Device> deviceList = deviceService.deviceList(deviceExample);
                    if (deviceList.size() > 0){
                        img = deviceList.get(0).getImg();
                        deviceName = deviceList.get(0).getDeviceName();
                    }
                    map.put("img",img);
                    map.put("deviceName",deviceName);
                    mapList.add(map);
                }

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
     * 新增售后报修订单
     */
    @RequestMapping(value = "/save/repairOrder")
    @ResponseBody
    public ResultEntity saveRepairOrder(@RequestAttribute String json, @RequestAttribute User user) {

        ResultEntity resultEntity = new ResultEntity();
        try {
            JsonObject jsonStr = new Gson().fromJson(json, JsonObject.class);
            String username = jsonStr.get("username").getAsString();
            if (StringUtils.isBlank(username)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            String telephone = jsonStr.get("telephone").getAsString();
            if (StringUtils.isBlank(telephone)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            String broadcaster = jsonStr.get("broadcaster").getAsString();
            if (StringUtils.isBlank(broadcaster)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            String color = jsonStr.get("color").getAsString();
            if (StringUtils.isBlank(color)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            String channel = jsonStr.get("channel").getAsString();
            if (StringUtils.isBlank(channel)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            String purchaseDate = jsonStr.get("purchaseDate").getAsString();
            if (StringUtils.isBlank(purchaseDate)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            String devicenumber = jsonStr.get("devicenumber").getAsString();
            if (StringUtils.isBlank(devicenumber)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            String faultPhenomenon = jsonStr.get("faultPhenomenon").getAsString();
            if (StringUtils.isBlank(faultPhenomenon)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            String faultDescription = jsonStr.get("faultDescription").getAsString();
            if (StringUtils.isBlank(faultDescription)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            String faultImg = jsonStr.get("faultImg").getAsString();
            if (StringUtils.isBlank(faultImg)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            String guaranteeDate = jsonStr.get("guaranteeDate").getAsString();
            if (StringUtils.isBlank(guaranteeDate)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            String paymentMethod = jsonStr.get("paymentMethod").getAsString();
            if (StringUtils.isBlank(paymentMethod)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            String proofImg = jsonStr.get("proofImg").getAsString();
            if (StringUtils.isBlank(proofImg)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }

            String guaranteeStatus = jsonStr.get("guaranteeStatus").getAsString();
            if (StringUtils.isBlank(proofImg)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }

            RepairOrder repairOrder = new RepairOrder();
            repairOrder.setUserId(user.getId());
            repairOrder.setUsername(username);
            repairOrder.setTelephone(telephone);
            repairOrder.setBroadcaster(broadcaster);
            repairOrder.setColor(color);
            repairOrder.setChannel(channel);
            repairOrder.setPurchaseDate(purchaseDate);
            repairOrder.setDevicenumber(Integer.parseInt(devicenumber));
            repairOrder.setFaultPhenomenon(faultPhenomenon);
            repairOrder.setFaultDescription(faultDescription);
            repairOrder.setFaultImg(faultImg);
            repairOrder.setGuaranteeDate(guaranteeDate);
            repairOrder.setPaymentMethod(Integer.parseInt(paymentMethod));
            repairOrder.setProofImg(proofImg);
            repairOrder.setOrderStatus(0);
            repairOrder.setGuaranteeStatus(Integer.parseInt(guaranteeStatus));
            Date date = new Date();
            repairOrder.setUpdateTime(date);
            repairOrder.setCreateTime(date);

            String attribute = "";
            DeviceExample deviceExample = new DeviceExample();
            deviceExample.or().andIsDelEqualTo(0).andDeviceNameEqualTo(repairOrder.getBroadcaster()).andColorEqualTo(repairOrder.getColor());
            List<Device> deviceList = deviceService.deviceList(deviceExample);
            if (deviceList.size() > 0){
                attribute = deviceList.get(0).getAttribute();
            }
            String guarStatus = "1";
            if ("0".equals(guaranteeStatus)){
                guarStatus = "2";
            }

            String ordernumber = DateUtil.formatTime(new Date()).substring(2) + guarStatus + attribute + TokenUtil.nextInt3();//220525143340 1 01 123
            repairOrder.setOrdernumber(ordernumber);
            repairOrderService.saveRepairOrder(repairOrder);
            //售后报修单提交成功通知
            String[] str = {"1",broadcaster};
            SmsUtil.getSms(telephone,"1192336",str);
            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
        }catch (Exception e){
            e.printStackTrace();
            resultEntity.setCode(CodeEnum.ERROR.getCode());
        }
        return resultEntity;
    }

    /**
     * 查看订单详情
     */
    @RequestMapping(value = "/repairOrder/get")
    @ResponseBody
    public ResultEntity getRepairOrderById(@RequestAttribute String json) {

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
            if (repairOrder == null){
                resultEntity.setCode(CodeEnum.SUCCESS_NO_FOUND.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            Map<String,Object> map = new HashMap<>();
            map.put("id", repairOrder.getId());
            map.put("username", repairOrder.getUsername());
            map.put("telephone", repairOrder.getTelephone());
            map.put("broadcaster", repairOrder.getBroadcaster());
            map.put("color", repairOrder.getColor());
            map.put("channel", repairOrder.getChannel());
            map.put("purchaseDate", repairOrder.getPurchaseDate());
            map.put("guaranteeDate", repairOrder.getGuaranteeDate());
            map.put("guaranteeStatus", repairOrder.getGuaranteeStatus());
            map.put("paymentMethod", repairOrder.getPaymentMethod());
            map.put("proofImg", repairOrder.getProofImg());
            map.put("orderStatus", repairOrder.getOrderStatus());
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
            map.put("createTime", DateUtil.toStringYmd(repairOrder.getCreateTime()));

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

            resultEntity.setData(map);
            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
        }catch (Exception e){
            e.printStackTrace();
            resultEntity.setCode(CodeEnum.ERROR.getCode());
        }
        return resultEntity;
    }

    /**
     * 填写回寄信息
     */
    @RequestMapping(value = "/save/repairAdder")
    @ResponseBody
    public ResultEntity saveRepairAdder(@RequestAttribute String json, @RequestAttribute User user) {
        ResultEntity resultEntity = new ResultEntity();
        try {
            JsonObject jsonStr = new Gson().fromJson(json, JsonObject.class);
            String repairOrderId = jsonStr.get("repairOrderId").getAsString();
            if (StringUtils.isBlank(repairOrderId)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }

            String repairExpress = jsonStr.get("repairExpress").getAsString();
            if (StringUtils.isBlank(repairExpress)){
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            String repairExpressNumber = jsonStr.get("repairExpressNumber").getAsString();
            if (StringUtils.isBlank(repairExpressNumber)){
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }

            RepairOrder repairOrder = repairOrderService.getRepairOrderById(Long.parseLong(repairOrderId));
            if (repairOrder == null) {
                resultEntity.setCode(CodeEnum.SUCCESS_NO_FOUND.getCode());
                resultEntity.setMsg("无订单记录");
                return resultEntity;
            }

            RepairOrder repair = new RepairOrder();
            repair.setId(repairOrder.getId());
            repair.setRepairExpress(repairExpress);
            repair.setRepairExpressNumber(repairExpressNumber);
            repair.setOrderStatus(2);
            repair.setUpdateTime(new Date());
            repairOrderService.updateRepairOrder(repair);
            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
        }catch (Exception e) {
            e.printStackTrace();
            resultEntity.setCode(CodeEnum.ERROR.getCode());
        }
        return resultEntity;
    }

    /**
     * 填写售后收货信息
     */
    @RequestMapping(value = "/save/takeAdder")
    @ResponseBody
    public ResultEntity saveTakeAdder(@RequestAttribute String json, @RequestAttribute User user) {
        ResultEntity resultEntity = new ResultEntity();
        try {
            JsonObject jsonStr = new Gson().fromJson(json, JsonObject.class);

            String repairOrderId = jsonStr.get("repairOrderId").getAsString();
            if (StringUtils.isBlank(repairOrderId)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }

            String takeName = jsonStr.get("takeName").getAsString();
            if (StringUtils.isBlank(takeName)){
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            String takeTelephone = jsonStr.get("takeTelephone").getAsString();
            if (StringUtils.isBlank(takeTelephone)){
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            String takeArea = jsonStr.get("takeArea").getAsString();
            if (StringUtils.isBlank(takeArea)){
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }
            String takeAddress = jsonStr.get("takeAddress").getAsString();
            if (StringUtils.isBlank(takeAddress)){
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

            RepairOrder repair = new RepairOrder();
            repair.setId(repairOrder.getId());
            repair.setTakeName(takeName);
            repair.setTakeTelephone(takeTelephone);
            repair.setTakeArea(takeArea);
            repair.setTakeAddress(takeAddress);
            Date date = new Date();
            repair.setUpdateTime(date);
            repairOrderService.updateRepairOrder(repair);
            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
        }catch (Exception e){
            resultEntity.setCode(CodeEnum.ERROR.getCode());
        }
        return resultEntity;
    }


    /**
     * 编辑售后报修订单
     */
    @RequestMapping(value = "/update/repairOrder")
    @ResponseBody
    public ResultEntity updateRepairOrder(@RequestAttribute String json, @RequestAttribute User user) {

        ResultEntity resultEntity = new ResultEntity();
        try {
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

            if ("0".equals(orderStatus) | "1".equals(orderStatus) | "2".equals(orderStatus) | "3".equals(orderStatus)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NO_RANGE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }

            RepairOrder repairOrder = new RepairOrder();
            repairOrder.setOrderStatus(Integer.parseInt(orderStatus));

            if (jsonStr.has("username")){
                String username = jsonStr.get("username").getAsString();
                if (StringUtils.isNotBlank(username)) {
                    repairOrder.setUsername(username);
                }
            }

            if (jsonStr.has("telephone")){
                String telephone = jsonStr.get("telephone").getAsString();
                if (StringUtils.isNotBlank(telephone)) {
                    repairOrder.setTelephone(telephone);
                }
            }

            if (jsonStr.has("broadcaster")){
                String broadcaster = jsonStr.get("broadcaster").getAsString();
                if (StringUtils.isNotBlank(broadcaster)) {
                    repairOrder.setBroadcaster(broadcaster);
                }
            }

            if (jsonStr.has("color")){
                String color = jsonStr.get("color").getAsString();
                if (StringUtils.isNotBlank(color)) {
                    repairOrder.setColor(color);
                }
            }

            if (jsonStr.has("channel")){
                String channel = jsonStr.get("channel").getAsString();
                if (StringUtils.isNotBlank(channel)) {
                    repairOrder.setChannel(channel);
                }
            }

            if (jsonStr.has("purchaseDate")){
                String purchaseDate = jsonStr.get("purchaseDate").getAsString();
                if (StringUtils.isNotBlank(purchaseDate)) {
                    repairOrder.setPurchaseDate(purchaseDate);
                }
            }

            if (jsonStr.has("devicenumber")){
                String devicenumber = jsonStr.get("devicenumber").getAsString();
                if (StringUtils.isNotBlank(devicenumber)) {
                    repairOrder.setDevicenumber(Integer.parseInt(devicenumber));
                }
            }

            if (jsonStr.has("faultPhenomenon")){
                String faultPhenomenon = jsonStr.get("faultPhenomenon").getAsString();
                if (StringUtils.isNotBlank(faultPhenomenon)) {
                    repairOrder.setFaultPhenomenon(faultPhenomenon);
                }
            }

            if (jsonStr.has("faultDescription")){
                String faultDescription = jsonStr.get("faultDescription").getAsString();
                if (StringUtils.isNotBlank(faultDescription)) {
                    repairOrder.setFaultDescription(faultDescription);
                }
            }

            if (jsonStr.has("faultImg")){
                String faultImg = jsonStr.get("faultImg").getAsString();
                if (StringUtils.isNotBlank(faultImg)) {
                    repairOrder.setFaultImg(faultImg);
                }
            }

            if (jsonStr.has("guaranteeDate")){
                String guaranteeDate = jsonStr.get("guaranteeDate").getAsString();
                if (StringUtils.isNotBlank(guaranteeDate)) {
                    repairOrder.setGuaranteeDate(guaranteeDate);
                }
            }

            if (jsonStr.has("paymentMethod")){
                String paymentMethod = jsonStr.get("paymentMethod").getAsString();
                if (StringUtils.isNotBlank(paymentMethod)) {
                    repairOrder.setPaymentMethod(Integer.parseInt(paymentMethod));
                }
            }

            if (jsonStr.has("proofImg")){
                String proofImg = jsonStr.get("proofImg").getAsString();
                if (StringUtils.isNotBlank(proofImg)) {
                    repairOrder.setProofImg(proofImg);
                }
            }

            if (jsonStr.has("guaranteeStatus")){
                String guaranteeStatus = jsonStr.get("guaranteeStatus").getAsString();
                if (StringUtils.isNotBlank(guaranteeStatus)) {
                    repairOrder.setGuaranteeStatus(Integer.parseInt(guaranteeStatus));
                }
            }

            repairOrder.setUpdateTime(new Date());
            repairOrder.setId(Long.parseLong(repairOrderId));
            repairOrderService.updateRepairOrder(repairOrder);

            if ("5".equals(orderStatus)) {//取消订单
                repairOrderSequenceService.isTimeRepairOrderSequence(Long.parseLong(repairOrderId),5,null);
                repairOrderOperateLogService.insertRepairOrderOperateLog(Long.parseLong(repairOrderId),6,"客户取消关闭了工单",user.getAccount());
            }

            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
        }catch (Exception e){
            e.printStackTrace();
            resultEntity.setCode(CodeEnum.ERROR.getCode());
        }
        return resultEntity;
    }

    /**
     * 再次申请售后报修订单
     */
    @RequestMapping(value = "/status/repairOrder")
    @ResponseBody
    public ResultEntity statusRepairOrder(@RequestAttribute String json) {
        ResultEntity resultEntity = new ResultEntity();
        try {
            JsonObject jsonStr = new Gson().fromJson(json, JsonObject.class);
            String repairOrderId = jsonStr.get("repairOrderId").getAsString();
            if (StringUtils.isBlank(repairOrderId)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }

            String broadcaster = jsonStr.get("broadcaster").getAsString();
            if (StringUtils.isBlank(broadcaster)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }

            String telephone = jsonStr.get("telephone").getAsString();
            if (StringUtils.isBlank(telephone)) {
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("数据有误，请检查");
                return resultEntity;
            }

            RepairOrder repairOrder = new RepairOrder();

            if (jsonStr.has("username")){
                String username = jsonStr.get("username").getAsString();
                if (StringUtils.isNotBlank(username)) {
                    repairOrder.setUsername(username);
                }
            }

            if (jsonStr.has("color")){
                String color = jsonStr.get("color").getAsString();
                if (StringUtils.isNotBlank(color)) {
                    repairOrder.setColor(color);
                }
            }

            if (jsonStr.has("channel")){
                String channel = jsonStr.get("channel").getAsString();
                if (StringUtils.isNotBlank(channel)) {
                    repairOrder.setChannel(channel);
                }
            }

            if (jsonStr.has("purchaseDate")){
                String purchaseDate = jsonStr.get("purchaseDate").getAsString();
                if (StringUtils.isNotBlank(purchaseDate)) {
                    repairOrder.setPurchaseDate(purchaseDate);
                }
            }

            if (jsonStr.has("devicenumber")){
                String devicenumber = jsonStr.get("devicenumber").getAsString();
                if (StringUtils.isNotBlank(devicenumber)) {
                    repairOrder.setDevicenumber(Integer.parseInt(devicenumber));
                }
            }

            if (jsonStr.has("faultPhenomenon")){
                String faultPhenomenon = jsonStr.get("faultPhenomenon").getAsString();
                if (StringUtils.isNotBlank(faultPhenomenon)) {
                    repairOrder.setFaultPhenomenon(faultPhenomenon);
                }
            }

            if (jsonStr.has("faultDescription")){
                String faultDescription = jsonStr.get("faultDescription").getAsString();
                if (StringUtils.isNotBlank(faultDescription)) {
                    repairOrder.setFaultDescription(faultDescription);
                }
            }

            if (jsonStr.has("faultImg")){
                String faultImg = jsonStr.get("faultImg").getAsString();
                if (StringUtils.isNotBlank(faultImg)) {
                    repairOrder.setFaultImg(faultImg);
                }
            }

            if (jsonStr.has("guaranteeDate")){
                String guaranteeDate = jsonStr.get("guaranteeDate").getAsString();
                if (StringUtils.isNotBlank(guaranteeDate)) {
                    repairOrder.setGuaranteeDate(guaranteeDate);
                }
            }

            if (jsonStr.has("paymentMethod")){
                String paymentMethod = jsonStr.get("paymentMethod").getAsString();
                if (StringUtils.isNotBlank(paymentMethod)) {
                    repairOrder.setPaymentMethod(Integer.parseInt(paymentMethod));
                }
            }

            if (jsonStr.has("proofImg")){
                String proofImg = jsonStr.get("proofImg").getAsString();
                if (StringUtils.isNotBlank(proofImg)) {
                    repairOrder.setProofImg(proofImg);
                }
            }

            if (jsonStr.has("guaranteeStatus")){
                String guaranteeStatus = jsonStr.get("guaranteeStatus").getAsString();
                if (StringUtils.isNotBlank(guaranteeStatus)) {
                    repairOrder.setGuaranteeStatus(Integer.parseInt(guaranteeStatus));
                }
            }
            repairOrder.setIsPay(1);
            repairOrder.setOrderStatus(0);
            repairOrder.setBroadcaster(broadcaster);
            repairOrder.setTelephone(telephone);
            repairOrder.setUpdateTime(new Date());

            repairOrder.setRefundMoney(new BigDecimal(0));
            repairOrder.setId(Long.parseLong(repairOrderId));

            RepairPayExample repairPayExample = new RepairPayExample();
            repairPayExample.or().andRepairOrderIdEqualTo(Long.parseLong(repairOrderId));
            List<RepairPay> payList = repairPayService.repairPayList(repairPayExample);
            if (payList.size() > 0){
                for (RepairPay repairPay : payList) {
                    RepairPay rep = new RepairPay();
                    rep.setId(repairPay.getId());
                    rep.setIsDel(1);
                    repairPayService.updateRepairPay(rep);
                }
            }

            RepairOrderSequence repairOrderSequence = repairOrderSequenceService.getSequenceByRepairOrderId(Long.parseLong(repairOrderId));
            if (repairOrderSequence != null) {
                RepairOrderSequence newRepairOrderSequence = new RepairOrderSequence();
                newRepairOrderSequence.setIsDel(1);
                newRepairOrderSequence.setId(repairOrderSequence.getId());
                repairOrderSequenceService.updateRepairOrderSequence(repairOrderSequence);
            }
            repairOrderService.updateRepairOrder(repairOrder);
            //售后报修单提交成功通知
            String[] str = {"1",broadcaster};
            SmsUtil.getSms(telephone,"1192336",str);
            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
        }catch (Exception e) {
            e.printStackTrace();
            resultEntity.setCode(CodeEnum.ERROR.getCode());
        }
        return resultEntity;
    }
}
