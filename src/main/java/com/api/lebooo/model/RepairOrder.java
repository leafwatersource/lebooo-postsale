package com.api.lebooo.model;

import java.math.BigDecimal;
import java.util.Date;

public class RepairOrder {
    private Long id;

    private Long userId;

    /**
     * 姓名
     */
    private String username;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 产品型号
     */
    private String broadcaster;

    /**
     * 产品颜色
     */
    private String color;

    /**
     * 购买渠道
     */
    private String channel;

    /**
     * 购买日期
     */
    private String purchaseDate;

    /**
     * 保修有效期
     */
    private String guaranteeDate;

    /**
     * 0 换新，1 维修
     */
    private Integer guaranteeStatus;

    /**
     * 0 保内免费，1 保外自费
     */
    private Integer paymentMethod;

    /**
     * 购买凭证截图，多张截图时用英文逗号隔开 (,)
     */
    private String proofImg;

    /**
     * 0 待审核，1 待寄回， 2 已寄回，3 已完成，4 拒绝，5 取消， 6 超时
     */
    private Integer orderStatus;

    /**
     * 故障现象
     */
    private String faultPhenomenon;

    /**
     * 故障描述
     */
    private String faultDescription;

    /**
     * 故障现象图片
     */
    private String faultImg;

    /**
     * 设备数
     */
    private Integer devicenumber;

    /**
     * 支付账号，0 填写，1 关闭
     */
    private Integer isPay;

    /**
     * 运费金额
     */
    private BigDecimal refundMoney;

    /**
     * 寄回维修物流公司
     */
    private String repairExpress;

    /**
     * 寄回维修快递单号
     */
    private String repairExpressNumber;

    /**
     * 收货人
     */
    private String takeName;

    /**
     * 收货电话
     */
    private String takeTelephone;

    /**
     * 收货地区
     */
    private String takeArea;

    /**
     * 收货详细地址
     */
    private String takeAddress;

    /**
     * 报修订单备注
     */
    private String remarks;

    /**
     * 订单号
     */
    private String ordernumber;

    private Integer isDel;

    private Date updateTime;

    private Date createTime;

    private Long accountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getBroadcaster() {
        return broadcaster;
    }

    public void setBroadcaster(String broadcaster) {
        this.broadcaster = broadcaster == null ? null : broadcaster.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate == null ? null : purchaseDate.trim();
    }

    public String getGuaranteeDate() {
        return guaranteeDate;
    }

    public void setGuaranteeDate(String guaranteeDate) {
        this.guaranteeDate = guaranteeDate == null ? null : guaranteeDate.trim();
    }

    public Integer getGuaranteeStatus() {
        return guaranteeStatus;
    }

    public void setGuaranteeStatus(Integer guaranteeStatus) {
        this.guaranteeStatus = guaranteeStatus;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getProofImg() {
        return proofImg;
    }

    public void setProofImg(String proofImg) {
        this.proofImg = proofImg == null ? null : proofImg.trim();
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getFaultPhenomenon() {
        return faultPhenomenon;
    }

    public void setFaultPhenomenon(String faultPhenomenon) {
        this.faultPhenomenon = faultPhenomenon == null ? null : faultPhenomenon.trim();
    }

    public String getFaultDescription() {
        return faultDescription;
    }

    public void setFaultDescription(String faultDescription) {
        this.faultDescription = faultDescription == null ? null : faultDescription.trim();
    }

    public String getFaultImg() {
        return faultImg;
    }

    public void setFaultImg(String faultImg) {
        this.faultImg = faultImg == null ? null : faultImg.trim();
    }

    public Integer getDevicenumber() {
        return devicenumber;
    }

    public void setDevicenumber(Integer devicenumber) {
        this.devicenumber = devicenumber;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }

    public String getRepairExpress() {
        return repairExpress;
    }

    public void setRepairExpress(String repairExpress) {
        this.repairExpress = repairExpress == null ? null : repairExpress.trim();
    }

    public String getRepairExpressNumber() {
        return repairExpressNumber;
    }

    public void setRepairExpressNumber(String repairExpressNumber) {
        this.repairExpressNumber = repairExpressNumber == null ? null : repairExpressNumber.trim();
    }

    public String getTakeName() {
        return takeName;
    }

    public void setTakeName(String takeName) {
        this.takeName = takeName == null ? null : takeName.trim();
    }

    public String getTakeTelephone() {
        return takeTelephone;
    }

    public void setTakeTelephone(String takeTelephone) {
        this.takeTelephone = takeTelephone == null ? null : takeTelephone.trim();
    }

    public String getTakeArea() {
        return takeArea;
    }

    public void setTakeArea(String takeArea) {
        this.takeArea = takeArea == null ? null : takeArea.trim();
    }

    public String getTakeAddress() {
        return takeAddress;
    }

    public void setTakeAddress(String takeAddress) {
        this.takeAddress = takeAddress == null ? null : takeAddress.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber == null ? null : ordernumber.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}