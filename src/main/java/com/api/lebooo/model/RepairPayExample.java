package com.api.lebooo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import me.fishlord.common.pageable.PageExample;

public class RepairPayExample extends PageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RepairPayExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
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

        public Criteria andRefundNameIsNull() {
            addCriterion("refund_name is null");
            return (Criteria) this;
        }

        public Criteria andRefundNameIsNotNull() {
            addCriterion("refund_name is not null");
            return (Criteria) this;
        }

        public Criteria andRefundNameEqualTo(String value) {
            addCriterion("refund_name =", value, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameNotEqualTo(String value) {
            addCriterion("refund_name <>", value, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameGreaterThan(String value) {
            addCriterion("refund_name >", value, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameGreaterThanOrEqualTo(String value) {
            addCriterion("refund_name >=", value, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameLessThan(String value) {
            addCriterion("refund_name <", value, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameLessThanOrEqualTo(String value) {
            addCriterion("refund_name <=", value, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameLike(String value) {
            addCriterion("refund_name like", value, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameNotLike(String value) {
            addCriterion("refund_name not like", value, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameIn(List<String> values) {
            addCriterion("refund_name in", values, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameNotIn(List<String> values) {
            addCriterion("refund_name not in", values, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameBetween(String value1, String value2) {
            addCriterion("refund_name between", value1, value2, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameNotBetween(String value1, String value2) {
            addCriterion("refund_name not between", value1, value2, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundTypeIsNull() {
            addCriterion("refund_type is null");
            return (Criteria) this;
        }

        public Criteria andRefundTypeIsNotNull() {
            addCriterion("refund_type is not null");
            return (Criteria) this;
        }

        public Criteria andRefundTypeEqualTo(String value) {
            addCriterion("refund_type =", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeNotEqualTo(String value) {
            addCriterion("refund_type <>", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeGreaterThan(String value) {
            addCriterion("refund_type >", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeGreaterThanOrEqualTo(String value) {
            addCriterion("refund_type >=", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeLessThan(String value) {
            addCriterion("refund_type <", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeLessThanOrEqualTo(String value) {
            addCriterion("refund_type <=", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeLike(String value) {
            addCriterion("refund_type like", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeNotLike(String value) {
            addCriterion("refund_type not like", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeIn(List<String> values) {
            addCriterion("refund_type in", values, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeNotIn(List<String> values) {
            addCriterion("refund_type not in", values, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeBetween(String value1, String value2) {
            addCriterion("refund_type between", value1, value2, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeNotBetween(String value1, String value2) {
            addCriterion("refund_type not between", value1, value2, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyIsNull() {
            addCriterion("refund_money is null");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyIsNotNull() {
            addCriterion("refund_money is not null");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyEqualTo(BigDecimal value) {
            addCriterion("refund_money =", value, "refundMoney");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyNotEqualTo(BigDecimal value) {
            addCriterion("refund_money <>", value, "refundMoney");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyGreaterThan(BigDecimal value) {
            addCriterion("refund_money >", value, "refundMoney");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_money >=", value, "refundMoney");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyLessThan(BigDecimal value) {
            addCriterion("refund_money <", value, "refundMoney");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_money <=", value, "refundMoney");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyIn(List<BigDecimal> values) {
            addCriterion("refund_money in", values, "refundMoney");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyNotIn(List<BigDecimal> values) {
            addCriterion("refund_money not in", values, "refundMoney");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_money between", value1, value2, "refundMoney");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_money not between", value1, value2, "refundMoney");
            return (Criteria) this;
        }

        public Criteria andRefundAccountIsNull() {
            addCriterion("refund_account is null");
            return (Criteria) this;
        }

        public Criteria andRefundAccountIsNotNull() {
            addCriterion("refund_account is not null");
            return (Criteria) this;
        }

        public Criteria andRefundAccountEqualTo(String value) {
            addCriterion("refund_account =", value, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountNotEqualTo(String value) {
            addCriterion("refund_account <>", value, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountGreaterThan(String value) {
            addCriterion("refund_account >", value, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountGreaterThanOrEqualTo(String value) {
            addCriterion("refund_account >=", value, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountLessThan(String value) {
            addCriterion("refund_account <", value, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountLessThanOrEqualTo(String value) {
            addCriterion("refund_account <=", value, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountLike(String value) {
            addCriterion("refund_account like", value, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountNotLike(String value) {
            addCriterion("refund_account not like", value, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountIn(List<String> values) {
            addCriterion("refund_account in", values, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountNotIn(List<String> values) {
            addCriterion("refund_account not in", values, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountBetween(String value1, String value2) {
            addCriterion("refund_account between", value1, value2, "refundAccount");
            return (Criteria) this;
        }

        public Criteria andRefundAccountNotBetween(String value1, String value2) {
            addCriterion("refund_account not between", value1, value2, "refundAccount");
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