package com.api.lebooo.model;

import java.util.Date;

public class RepairOrderSequence {
    private Long id;

    private Long repairOrderId;

    /**
     * 订单最新标记状态。 0 待客退，1 跟进，2 完结，3 待收地址，4 待给仓库，5 需建单，6 待给快递，7 物流停发
     */
    private Integer isMark;

    /**
     * 分配时间
     */
    private Date distriTime;

    /**
     * 待寄回时间
     */
    private Date waitTime;

    /**
     * 已寄回时间
     */
    private Date sendTime;

    /**
     * 已完成时间
     */
    private Date finishTime;

    /**
     * 拒绝时间
     */
    private Date rejectTime;

    /**
     * 取消时间
     */
    private Date cancelTime;

    /**
     * 超时时间
     */
    private Date exceedTime;

    private Long accountId;

    private Integer isDel;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRepairOrderId() {
        return repairOrderId;
    }

    public void setRepairOrderId(Long repairOrderId) {
        this.repairOrderId = repairOrderId;
    }

    public Integer getIsMark() {
        return isMark;
    }

    public void setIsMark(Integer isMark) {
        this.isMark = isMark;
    }

    public Date getDistriTime() {
        return distriTime;
    }

    public void setDistriTime(Date distriTime) {
        this.distriTime = distriTime;
    }

    public Date getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Date waitTime) {
        this.waitTime = waitTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getRejectTime() {
        return rejectTime;
    }

    public void setRejectTime(Date rejectTime) {
        this.rejectTime = rejectTime;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Date getExceedTime() {
        return exceedTime;
    }

    public void setExceedTime(Date exceedTime) {
        this.exceedTime = exceedTime;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}