package com.api.lebooo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import me.fishlord.common.pageable.PageExample;

public class RepairOrderSequenceExample extends PageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RepairOrderSequenceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRepairOrderIdIsNull() {
            addCriterion("repair_order_id is null");
            return (Criteria) this;
        }

        public Criteria andRepairOrderIdIsNotNull() {
            addCriterion("repair_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andRepairOrderIdEqualTo(Long value) {
            addCriterion("repair_order_id =", value, "repairOrderId");
            return (Criteria) this;
        }

        public Criteria andRepairOrderIdNotEqualTo(Long value) {
            addCriterion("repair_order_id <>", value, "repairOrderId");
            return (Criteria) this;
        }

        public Criteria andRepairOrderIdGreaterThan(Long value) {
            addCriterion("repair_order_id >", value, "repairOrderId");
            return (Criteria) this;
        }

        public Criteria andRepairOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("repair_order_id >=", value, "repairOrderId");
            return (Criteria) this;
        }

        public Criteria andRepairOrderIdLessThan(Long value) {
            addCriterion("repair_order_id <", value, "repairOrderId");
            return (Criteria) this;
        }

        public Criteria andRepairOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("repair_order_id <=", value, "repairOrderId");
            return (Criteria) this;
        }

        public Criteria andRepairOrderIdIn(List<Long> values) {
            addCriterion("repair_order_id in", values, "repairOrderId");
            return (Criteria) this;
        }

        public Criteria andRepairOrderIdNotIn(List<Long> values) {
            addCriterion("repair_order_id not in", values, "repairOrderId");
            return (Criteria) this;
        }

        public Criteria andRepairOrderIdBetween(Long value1, Long value2) {
            addCriterion("repair_order_id between", value1, value2, "repairOrderId");
            return (Criteria) this;
        }

        public Criteria andRepairOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("repair_order_id not between", value1, value2, "repairOrderId");
            return (Criteria) this;
        }

        public Criteria andIsMarkIsNull() {
            addCriterion("is_mark is null");
            return (Criteria) this;
        }

        public Criteria andIsMarkIsNotNull() {
            addCriterion("is_mark is not null");
            return (Criteria) this;
        }

        public Criteria andIsMarkEqualTo(Integer value) {
            addCriterion("is_mark =", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkNotEqualTo(Integer value) {
            addCriterion("is_mark <>", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkGreaterThan(Integer value) {
            addCriterion("is_mark >", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_mark >=", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkLessThan(Integer value) {
            addCriterion("is_mark <", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkLessThanOrEqualTo(Integer value) {
            addCriterion("is_mark <=", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkIn(List<Integer> values) {
            addCriterion("is_mark in", values, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkNotIn(List<Integer> values) {
            addCriterion("is_mark not in", values, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkBetween(Integer value1, Integer value2) {
            addCriterion("is_mark between", value1, value2, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkNotBetween(Integer value1, Integer value2) {
            addCriterion("is_mark not between", value1, value2, "isMark");
            return (Criteria) this;
        }

        public Criteria andDistriTimeIsNull() {
            addCriterion("distri_time is null");
            return (Criteria) this;
        }

        public Criteria andDistriTimeIsNotNull() {
            addCriterion("distri_time is not null");
            return (Criteria) this;
        }

        public Criteria andDistriTimeEqualTo(Date value) {
            addCriterion("distri_time =", value, "distriTime");
            return (Criteria) this;
        }

        public Criteria andDistriTimeNotEqualTo(Date value) {
            addCriterion("distri_time <>", value, "distriTime");
            return (Criteria) this;
        }

        public Criteria andDistriTimeGreaterThan(Date value) {
            addCriterion("distri_time >", value, "distriTime");
            return (Criteria) this;
        }

        public Criteria andDistriTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("distri_time >=", value, "distriTime");
            return (Criteria) this;
        }

        public Criteria andDistriTimeLessThan(Date value) {
            addCriterion("distri_time <", value, "distriTime");
            return (Criteria) this;
        }

        public Criteria andDistriTimeLessThanOrEqualTo(Date value) {
            addCriterion("distri_time <=", value, "distriTime");
            return (Criteria) this;
        }

        public Criteria andDistriTimeIn(List<Date> values) {
            addCriterion("distri_time in", values, "distriTime");
            return (Criteria) this;
        }

        public Criteria andDistriTimeNotIn(List<Date> values) {
            addCriterion("distri_time not in", values, "distriTime");
            return (Criteria) this;
        }

        public Criteria andDistriTimeBetween(Date value1, Date value2) {
            addCriterion("distri_time between", value1, value2, "distriTime");
            return (Criteria) this;
        }

        public Criteria andDistriTimeNotBetween(Date value1, Date value2) {
            addCriterion("distri_time not between", value1, value2, "distriTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeIsNull() {
            addCriterion("wait_time is null");
            return (Criteria) this;
        }

        public Criteria andWaitTimeIsNotNull() {
            addCriterion("wait_time is not null");
            return (Criteria) this;
        }

        public Criteria andWaitTimeEqualTo(Date value) {
            addCriterion("wait_time =", value, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeNotEqualTo(Date value) {
            addCriterion("wait_time <>", value, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeGreaterThan(Date value) {
            addCriterion("wait_time >", value, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("wait_time >=", value, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeLessThan(Date value) {
            addCriterion("wait_time <", value, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeLessThanOrEqualTo(Date value) {
            addCriterion("wait_time <=", value, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeIn(List<Date> values) {
            addCriterion("wait_time in", values, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeNotIn(List<Date> values) {
            addCriterion("wait_time not in", values, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeBetween(Date value1, Date value2) {
            addCriterion("wait_time between", value1, value2, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeNotBetween(Date value1, Date value2) {
            addCriterion("wait_time not between", value1, value2, "waitTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("send_time is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("send_time is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("send_time =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("send_time <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("send_time >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("send_time >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("send_time <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("send_time <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("send_time in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("send_time not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("send_time between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("send_time not between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNull() {
            addCriterion("finish_time is null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNotNull() {
            addCriterion("finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeEqualTo(Date value) {
            addCriterion("finish_time =", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotEqualTo(Date value) {
            addCriterion("finish_time <>", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThan(Date value) {
            addCriterion("finish_time >", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("finish_time >=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThan(Date value) {
            addCriterion("finish_time <", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThanOrEqualTo(Date value) {
            addCriterion("finish_time <=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIn(List<Date> values) {
            addCriterion("finish_time in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotIn(List<Date> values) {
            addCriterion("finish_time not in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeBetween(Date value1, Date value2) {
            addCriterion("finish_time between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotBetween(Date value1, Date value2) {
            addCriterion("finish_time not between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andRejectTimeIsNull() {
            addCriterion("reject_time is null");
            return (Criteria) this;
        }

        public Criteria andRejectTimeIsNotNull() {
            addCriterion("reject_time is not null");
            return (Criteria) this;
        }

        public Criteria andRejectTimeEqualTo(Date value) {
            addCriterion("reject_time =", value, "rejectTime");
            return (Criteria) this;
        }

        public Criteria andRejectTimeNotEqualTo(Date value) {
            addCriterion("reject_time <>", value, "rejectTime");
            return (Criteria) this;
        }

        public Criteria andRejectTimeGreaterThan(Date value) {
            addCriterion("reject_time >", value, "rejectTime");
            return (Criteria) this;
        }

        public Criteria andRejectTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("reject_time >=", value, "rejectTime");
            return (Criteria) this;
        }

        public Criteria andRejectTimeLessThan(Date value) {
            addCriterion("reject_time <", value, "rejectTime");
            return (Criteria) this;
        }

        public Criteria andRejectTimeLessThanOrEqualTo(Date value) {
            addCriterion("reject_time <=", value, "rejectTime");
            return (Criteria) this;
        }

        public Criteria andRejectTimeIn(List<Date> values) {
            addCriterion("reject_time in", values, "rejectTime");
            return (Criteria) this;
        }

        public Criteria andRejectTimeNotIn(List<Date> values) {
            addCriterion("reject_time not in", values, "rejectTime");
            return (Criteria) this;
        }

        public Criteria andRejectTimeBetween(Date value1, Date value2) {
            addCriterion("reject_time between", value1, value2, "rejectTime");
            return (Criteria) this;
        }

        public Criteria andRejectTimeNotBetween(Date value1, Date value2) {
            addCriterion("reject_time not between", value1, value2, "rejectTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeIsNull() {
            addCriterion("cancel_time is null");
            return (Criteria) this;
        }

        public Criteria andCancelTimeIsNotNull() {
            addCriterion("cancel_time is not null");
            return (Criteria) this;
        }

        public Criteria andCancelTimeEqualTo(Date value) {
            addCriterion("cancel_time =", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotEqualTo(Date value) {
            addCriterion("cancel_time <>", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeGreaterThan(Date value) {
            addCriterion("cancel_time >", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("cancel_time >=", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeLessThan(Date value) {
            addCriterion("cancel_time <", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeLessThanOrEqualTo(Date value) {
            addCriterion("cancel_time <=", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeIn(List<Date> values) {
            addCriterion("cancel_time in", values, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotIn(List<Date> values) {
            addCriterion("cancel_time not in", values, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeBetween(Date value1, Date value2) {
            addCriterion("cancel_time between", value1, value2, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotBetween(Date value1, Date value2) {
            addCriterion("cancel_time not between", value1, value2, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andExceedTimeIsNull() {
            addCriterion("exceed_time is null");
            return (Criteria) this;
        }

        public Criteria andExceedTimeIsNotNull() {
            addCriterion("exceed_time is not null");
            return (Criteria) this;
        }

        public Criteria andExceedTimeEqualTo(Date value) {
            addCriterion("exceed_time =", value, "exceedTime");
            return (Criteria) this;
        }

        public Criteria andExceedTimeNotEqualTo(Date value) {
            addCriterion("exceed_time <>", value, "exceedTime");
            return (Criteria) this;
        }

        public Criteria andExceedTimeGreaterThan(Date value) {
            addCriterion("exceed_time >", value, "exceedTime");
            return (Criteria) this;
        }

        public Criteria andExceedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("exceed_time >=", value, "exceedTime");
            return (Criteria) this;
        }

        public Criteria andExceedTimeLessThan(Date value) {
            addCriterion("exceed_time <", value, "exceedTime");
            return (Criteria) this;
        }

        public Criteria andExceedTimeLessThanOrEqualTo(Date value) {
            addCriterion("exceed_time <=", value, "exceedTime");
            return (Criteria) this;
        }

        public Criteria andExceedTimeIn(List<Date> values) {
            addCriterion("exceed_time in", values, "exceedTime");
            return (Criteria) this;
        }

        public Criteria andExceedTimeNotIn(List<Date> values) {
            addCriterion("exceed_time not in", values, "exceedTime");
            return (Criteria) this;
        }

        public Criteria andExceedTimeBetween(Date value1, Date value2) {
            addCriterion("exceed_time between", value1, value2, "exceedTime");
            return (Criteria) this;
        }

        public Criteria andExceedTimeNotBetween(Date value1, Date value2) {
            addCriterion("exceed_time not between", value1, value2, "exceedTime");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNull() {
            addCriterion("account_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNotNull() {
            addCriterion("account_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIdEqualTo(Long value) {
            addCriterion("account_id =", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotEqualTo(Long value) {
            addCriterion("account_id <>", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThan(Long value) {
            addCriterion("account_id >", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThanOrEqualTo(Long value) {
            addCriterion("account_id >=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThan(Long value) {
            addCriterion("account_id <", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThanOrEqualTo(Long value) {
            addCriterion("account_id <=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIn(List<Long> values) {
            addCriterion("account_id in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotIn(List<Long> values) {
            addCriterion("account_id not in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdBetween(Long value1, Long value2) {
            addCriterion("account_id between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotBetween(Long value1, Long value2) {
            addCriterion("account_id not between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Integer value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Integer value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Integer value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Integer value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Integer value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Integer> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Integer> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Integer value1, Integer value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Integer value1, Integer value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}