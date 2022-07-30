package com.api.lebooo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import me.fishlord.common.pageable.PageExample;

public class RepairOrderExample extends PageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RepairOrderExample() {
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

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("telephone is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("telephone is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("telephone =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("telephone <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("telephone >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("telephone >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("telephone <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("telephone <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("telephone like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("telephone not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("telephone in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("telephone not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("telephone between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("telephone not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andBroadcasterIsNull() {
            addCriterion("broadcaster is null");
            return (Criteria) this;
        }

        public Criteria andBroadcasterIsNotNull() {
            addCriterion("broadcaster is not null");
            return (Criteria) this;
        }

        public Criteria andBroadcasterEqualTo(String value) {
            addCriterion("broadcaster =", value, "broadcaster");
            return (Criteria) this;
        }

        public Criteria andBroadcasterNotEqualTo(String value) {
            addCriterion("broadcaster <>", value, "broadcaster");
            return (Criteria) this;
        }

        public Criteria andBroadcasterGreaterThan(String value) {
            addCriterion("broadcaster >", value, "broadcaster");
            return (Criteria) this;
        }

        public Criteria andBroadcasterGreaterThanOrEqualTo(String value) {
            addCriterion("broadcaster >=", value, "broadcaster");
            return (Criteria) this;
        }

        public Criteria andBroadcasterLessThan(String value) {
            addCriterion("broadcaster <", value, "broadcaster");
            return (Criteria) this;
        }

        public Criteria andBroadcasterLessThanOrEqualTo(String value) {
            addCriterion("broadcaster <=", value, "broadcaster");
            return (Criteria) this;
        }

        public Criteria andBroadcasterLike(String value) {
            addCriterion("broadcaster like", value, "broadcaster");
            return (Criteria) this;
        }

        public Criteria andBroadcasterNotLike(String value) {
            addCriterion("broadcaster not like", value, "broadcaster");
            return (Criteria) this;
        }

        public Criteria andBroadcasterIn(List<String> values) {
            addCriterion("broadcaster in", values, "broadcaster");
            return (Criteria) this;
        }

        public Criteria andBroadcasterNotIn(List<String> values) {
            addCriterion("broadcaster not in", values, "broadcaster");
            return (Criteria) this;
        }

        public Criteria andBroadcasterBetween(String value1, String value2) {
            addCriterion("broadcaster between", value1, value2, "broadcaster");
            return (Criteria) this;
        }

        public Criteria andBroadcasterNotBetween(String value1, String value2) {
            addCriterion("broadcaster not between", value1, value2, "broadcaster");
            return (Criteria) this;
        }

        public Criteria andColorIsNull() {
            addCriterion("color is null");
            return (Criteria) this;
        }

        public Criteria andColorIsNotNull() {
            addCriterion("color is not null");
            return (Criteria) this;
        }

        public Criteria andColorEqualTo(String value) {
            addCriterion("color =", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotEqualTo(String value) {
            addCriterion("color <>", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThan(String value) {
            addCriterion("color >", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThanOrEqualTo(String value) {
            addCriterion("color >=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThan(String value) {
            addCriterion("color <", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThanOrEqualTo(String value) {
            addCriterion("color <=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLike(String value) {
            addCriterion("color like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotLike(String value) {
            addCriterion("color not like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorIn(List<String> values) {
            addCriterion("color in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotIn(List<String> values) {
            addCriterion("color not in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorBetween(String value1, String value2) {
            addCriterion("color between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotBetween(String value1, String value2) {
            addCriterion("color not between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andChannelIsNull() {
            addCriterion("channel is null");
            return (Criteria) this;
        }

        public Criteria andChannelIsNotNull() {
            addCriterion("channel is not null");
            return (Criteria) this;
        }

        public Criteria andChannelEqualTo(String value) {
            addCriterion("channel =", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotEqualTo(String value) {
            addCriterion("channel <>", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThan(String value) {
            addCriterion("channel >", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThanOrEqualTo(String value) {
            addCriterion("channel >=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThan(String value) {
            addCriterion("channel <", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThanOrEqualTo(String value) {
            addCriterion("channel <=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLike(String value) {
            addCriterion("channel like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotLike(String value) {
            addCriterion("channel not like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelIn(List<String> values) {
            addCriterion("channel in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotIn(List<String> values) {
            addCriterion("channel not in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelBetween(String value1, String value2) {
            addCriterion("channel between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotBetween(String value1, String value2) {
            addCriterion("channel not between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateIsNull() {
            addCriterion("purchase_date is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateIsNotNull() {
            addCriterion("purchase_date is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateEqualTo(String value) {
            addCriterion("purchase_date =", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateNotEqualTo(String value) {
            addCriterion("purchase_date <>", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateGreaterThan(String value) {
            addCriterion("purchase_date >", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateGreaterThanOrEqualTo(String value) {
            addCriterion("purchase_date >=", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateLessThan(String value) {
            addCriterion("purchase_date <", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateLessThanOrEqualTo(String value) {
            addCriterion("purchase_date <=", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateLike(String value) {
            addCriterion("purchase_date like", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateNotLike(String value) {
            addCriterion("purchase_date not like", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateIn(List<String> values) {
            addCriterion("purchase_date in", values, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateNotIn(List<String> values) {
            addCriterion("purchase_date not in", values, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateBetween(String value1, String value2) {
            addCriterion("purchase_date between", value1, value2, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateNotBetween(String value1, String value2) {
            addCriterion("purchase_date not between", value1, value2, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andGuaranteeDateIsNull() {
            addCriterion("guarantee_date is null");
            return (Criteria) this;
        }

        public Criteria andGuaranteeDateIsNotNull() {
            addCriterion("guarantee_date is not null");
            return (Criteria) this;
        }

        public Criteria andGuaranteeDateEqualTo(String value) {
            addCriterion("guarantee_date =", value, "guaranteeDate");
            return (Criteria) this;
        }

        public Criteria andGuaranteeDateNotEqualTo(String value) {
            addCriterion("guarantee_date <>", value, "guaranteeDate");
            return (Criteria) this;
        }

        public Criteria andGuaranteeDateGreaterThan(String value) {
            addCriterion("guarantee_date >", value, "guaranteeDate");
            return (Criteria) this;
        }

        public Criteria andGuaranteeDateGreaterThanOrEqualTo(String value) {
            addCriterion("guarantee_date >=", value, "guaranteeDate");
            return (Criteria) this;
        }

        public Criteria andGuaranteeDateLessThan(String value) {
            addCriterion("guarantee_date <", value, "guaranteeDate");
            return (Criteria) this;
        }

        public Criteria andGuaranteeDateLessThanOrEqualTo(String value) {
            addCriterion("guarantee_date <=", value, "guaranteeDate");
            return (Criteria) this;
        }

        public Criteria andGuaranteeDateLike(String value) {
            addCriterion("guarantee_date like", value, "guaranteeDate");
            return (Criteria) this;
        }

        public Criteria andGuaranteeDateNotLike(String value) {
            addCriterion("guarantee_date not like", value, "guaranteeDate");
            return (Criteria) this;
        }

        public Criteria andGuaranteeDateIn(List<String> values) {
            addCriterion("guarantee_date in", values, "guaranteeDate");
            return (Criteria) this;
        }

        public Criteria andGuaranteeDateNotIn(List<String> values) {
            addCriterion("guarantee_date not in", values, "guaranteeDate");
            return (Criteria) this;
        }

        public Criteria andGuaranteeDateBetween(String value1, String value2) {
            addCriterion("guarantee_date between", value1, value2, "guaranteeDate");
            return (Criteria) this;
        }

        public Criteria andGuaranteeDateNotBetween(String value1, String value2) {
            addCriterion("guarantee_date not between", value1, value2, "guaranteeDate");
            return (Criteria) this;
        }

        public Criteria andGuaranteeStatusIsNull() {
            addCriterion("guarantee_status is null");
            return (Criteria) this;
        }

        public Criteria andGuaranteeStatusIsNotNull() {
            addCriterion("guarantee_status is not null");
            return (Criteria) this;
        }

        public Criteria andGuaranteeStatusEqualTo(Integer value) {
            addCriterion("guarantee_status =", value, "guaranteeStatus");
            return (Criteria) this;
        }

        public Criteria andGuaranteeStatusNotEqualTo(Integer value) {
            addCriterion("guarantee_status <>", value, "guaranteeStatus");
            return (Criteria) this;
        }

        public Criteria andGuaranteeStatusGreaterThan(Integer value) {
            addCriterion("guarantee_status >", value, "guaranteeStatus");
            return (Criteria) this;
        }

        public Criteria andGuaranteeStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("guarantee_status >=", value, "guaranteeStatus");
            return (Criteria) this;
        }

        public Criteria andGuaranteeStatusLessThan(Integer value) {
            addCriterion("guarantee_status <", value, "guaranteeStatus");
            return (Criteria) this;
        }

        public Criteria andGuaranteeStatusLessThanOrEqualTo(Integer value) {
            addCriterion("guarantee_status <=", value, "guaranteeStatus");
            return (Criteria) this;
        }

        public Criteria andGuaranteeStatusIn(List<Integer> values) {
            addCriterion("guarantee_status in", values, "guaranteeStatus");
            return (Criteria) this;
        }

        public Criteria andGuaranteeStatusNotIn(List<Integer> values) {
            addCriterion("guarantee_status not in", values, "guaranteeStatus");
            return (Criteria) this;
        }

        public Criteria andGuaranteeStatusBetween(Integer value1, Integer value2) {
            addCriterion("guarantee_status between", value1, value2, "guaranteeStatus");
            return (Criteria) this;
        }

        public Criteria andGuaranteeStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("guarantee_status not between", value1, value2, "guaranteeStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIsNull() {
            addCriterion("payment_method is null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIsNotNull() {
            addCriterion("payment_method is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodEqualTo(Integer value) {
            addCriterion("payment_method =", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotEqualTo(Integer value) {
            addCriterion("payment_method <>", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodGreaterThan(Integer value) {
            addCriterion("payment_method >", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodGreaterThanOrEqualTo(Integer value) {
            addCriterion("payment_method >=", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLessThan(Integer value) {
            addCriterion("payment_method <", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLessThanOrEqualTo(Integer value) {
            addCriterion("payment_method <=", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIn(List<Integer> values) {
            addCriterion("payment_method in", values, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotIn(List<Integer> values) {
            addCriterion("payment_method not in", values, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodBetween(Integer value1, Integer value2) {
            addCriterion("payment_method between", value1, value2, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotBetween(Integer value1, Integer value2) {
            addCriterion("payment_method not between", value1, value2, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andProofImgIsNull() {
            addCriterion("proof_img is null");
            return (Criteria) this;
        }

        public Criteria andProofImgIsNotNull() {
            addCriterion("proof_img is not null");
            return (Criteria) this;
        }

        public Criteria andProofImgEqualTo(String value) {
            addCriterion("proof_img =", value, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgNotEqualTo(String value) {
            addCriterion("proof_img <>", value, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgGreaterThan(String value) {
            addCriterion("proof_img >", value, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgGreaterThanOrEqualTo(String value) {
            addCriterion("proof_img >=", value, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgLessThan(String value) {
            addCriterion("proof_img <", value, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgLessThanOrEqualTo(String value) {
            addCriterion("proof_img <=", value, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgLike(String value) {
            addCriterion("proof_img like", value, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgNotLike(String value) {
            addCriterion("proof_img not like", value, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgIn(List<String> values) {
            addCriterion("proof_img in", values, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgNotIn(List<String> values) {
            addCriterion("proof_img not in", values, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgBetween(String value1, String value2) {
            addCriterion("proof_img between", value1, value2, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgNotBetween(String value1, String value2) {
            addCriterion("proof_img not between", value1, value2, "proofImg");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNull() {
            addCriterion("order__status is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("order__status is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(Integer value) {
            addCriterion("order__status =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(Integer value) {
            addCriterion("order__status <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(Integer value) {
            addCriterion("order__status >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("order__status >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(Integer value) {
            addCriterion("order__status <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(Integer value) {
            addCriterion("order__status <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<Integer> values) {
            addCriterion("order__status in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<Integer> values) {
            addCriterion("order__status not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(Integer value1, Integer value2) {
            addCriterion("order__status between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("order__status not between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andFaultPhenomenonIsNull() {
            addCriterion("fault_phenomenon is null");
            return (Criteria) this;
        }

        public Criteria andFaultPhenomenonIsNotNull() {
            addCriterion("fault_phenomenon is not null");
            return (Criteria) this;
        }

        public Criteria andFaultPhenomenonEqualTo(String value) {
            addCriterion("fault_phenomenon =", value, "faultPhenomenon");
            return (Criteria) this;
        }

        public Criteria andFaultPhenomenonNotEqualTo(String value) {
            addCriterion("fault_phenomenon <>", value, "faultPhenomenon");
            return (Criteria) this;
        }

        public Criteria andFaultPhenomenonGreaterThan(String value) {
            addCriterion("fault_phenomenon >", value, "faultPhenomenon");
            return (Criteria) this;
        }

        public Criteria andFaultPhenomenonGreaterThanOrEqualTo(String value) {
            addCriterion("fault_phenomenon >=", value, "faultPhenomenon");
            return (Criteria) this;
        }

        public Criteria andFaultPhenomenonLessThan(String value) {
            addCriterion("fault_phenomenon <", value, "faultPhenomenon");
            return (Criteria) this;
        }

        public Criteria andFaultPhenomenonLessThanOrEqualTo(String value) {
            addCriterion("fault_phenomenon <=", value, "faultPhenomenon");
            return (Criteria) this;
        }

        public Criteria andFaultPhenomenonLike(String value) {
            addCriterion("fault_phenomenon like", value, "faultPhenomenon");
            return (Criteria) this;
        }

        public Criteria andFaultPhenomenonNotLike(String value) {
            addCriterion("fault_phenomenon not like", value, "faultPhenomenon");
            return (Criteria) this;
        }

        public Criteria andFaultPhenomenonIn(List<String> values) {
            addCriterion("fault_phenomenon in", values, "faultPhenomenon");
            return (Criteria) this;
        }

        public Criteria andFaultPhenomenonNotIn(List<String> values) {
            addCriterion("fault_phenomenon not in", values, "faultPhenomenon");
            return (Criteria) this;
        }

        public Criteria andFaultPhenomenonBetween(String value1, String value2) {
            addCriterion("fault_phenomenon between", value1, value2, "faultPhenomenon");
            return (Criteria) this;
        }

        public Criteria andFaultPhenomenonNotBetween(String value1, String value2) {
            addCriterion("fault_phenomenon not between", value1, value2, "faultPhenomenon");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionIsNull() {
            addCriterion("fault_description is null");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionIsNotNull() {
            addCriterion("fault_description is not null");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionEqualTo(String value) {
            addCriterion("fault_description =", value, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionNotEqualTo(String value) {
            addCriterion("fault_description <>", value, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionGreaterThan(String value) {
            addCriterion("fault_description >", value, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("fault_description >=", value, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionLessThan(String value) {
            addCriterion("fault_description <", value, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionLessThanOrEqualTo(String value) {
            addCriterion("fault_description <=", value, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionLike(String value) {
            addCriterion("fault_description like", value, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionNotLike(String value) {
            addCriterion("fault_description not like", value, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionIn(List<String> values) {
            addCriterion("fault_description in", values, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionNotIn(List<String> values) {
            addCriterion("fault_description not in", values, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionBetween(String value1, String value2) {
            addCriterion("fault_description between", value1, value2, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionNotBetween(String value1, String value2) {
            addCriterion("fault_description not between", value1, value2, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultImgIsNull() {
            addCriterion("fault_img is null");
            return (Criteria) this;
        }

        public Criteria andFaultImgIsNotNull() {
            addCriterion("fault_img is not null");
            return (Criteria) this;
        }

        public Criteria andFaultImgEqualTo(String value) {
            addCriterion("fault_img =", value, "faultImg");
            return (Criteria) this;
        }

        public Criteria andFaultImgNotEqualTo(String value) {
            addCriterion("fault_img <>", value, "faultImg");
            return (Criteria) this;
        }

        public Criteria andFaultImgGreaterThan(String value) {
            addCriterion("fault_img >", value, "faultImg");
            return (Criteria) this;
        }

        public Criteria andFaultImgGreaterThanOrEqualTo(String value) {
            addCriterion("fault_img >=", value, "faultImg");
            return (Criteria) this;
        }

        public Criteria andFaultImgLessThan(String value) {
            addCriterion("fault_img <", value, "faultImg");
            return (Criteria) this;
        }

        public Criteria andFaultImgLessThanOrEqualTo(String value) {
            addCriterion("fault_img <=", value, "faultImg");
            return (Criteria) this;
        }

        public Criteria andFaultImgLike(String value) {
            addCriterion("fault_img like", value, "faultImg");
            return (Criteria) this;
        }

        public Criteria andFaultImgNotLike(String value) {
            addCriterion("fault_img not like", value, "faultImg");
            return (Criteria) this;
        }

        public Criteria andFaultImgIn(List<String> values) {
            addCriterion("fault_img in", values, "faultImg");
            return (Criteria) this;
        }

        public Criteria andFaultImgNotIn(List<String> values) {
            addCriterion("fault_img not in", values, "faultImg");
            return (Criteria) this;
        }

        public Criteria andFaultImgBetween(String value1, String value2) {
            addCriterion("fault_img between", value1, value2, "faultImg");
            return (Criteria) this;
        }

        public Criteria andFaultImgNotBetween(String value1, String value2) {
            addCriterion("fault_img not between", value1, value2, "faultImg");
            return (Criteria) this;
        }

        public Criteria andDevicenumberIsNull() {
            addCriterion("devicenumber is null");
            return (Criteria) this;
        }

        public Criteria andDevicenumberIsNotNull() {
            addCriterion("devicenumber is not null");
            return (Criteria) this;
        }

        public Criteria andDevicenumberEqualTo(Integer value) {
            addCriterion("devicenumber =", value, "devicenumber");
            return (Criteria) this;
        }

        public Criteria andDevicenumberNotEqualTo(Integer value) {
            addCriterion("devicenumber <>", value, "devicenumber");
            return (Criteria) this;
        }

        public Criteria andDevicenumberGreaterThan(Integer value) {
            addCriterion("devicenumber >", value, "devicenumber");
            return (Criteria) this;
        }

        public Criteria andDevicenumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("devicenumber >=", value, "devicenumber");
            return (Criteria) this;
        }

        public Criteria andDevicenumberLessThan(Integer value) {
            addCriterion("devicenumber <", value, "devicenumber");
            return (Criteria) this;
        }

        public Criteria andDevicenumberLessThanOrEqualTo(Integer value) {
            addCriterion("devicenumber <=", value, "devicenumber");
            return (Criteria) this;
        }

        public Criteria andDevicenumberIn(List<Integer> values) {
            addCriterion("devicenumber in", values, "devicenumber");
            return (Criteria) this;
        }

        public Criteria andDevicenumberNotIn(List<Integer> values) {
            addCriterion("devicenumber not in", values, "devicenumber");
            return (Criteria) this;
        }

        public Criteria andDevicenumberBetween(Integer value1, Integer value2) {
            addCriterion("devicenumber between", value1, value2, "devicenumber");
            return (Criteria) this;
        }

        public Criteria andDevicenumberNotBetween(Integer value1, Integer value2) {
            addCriterion("devicenumber not between", value1, value2, "devicenumber");
            return (Criteria) this;
        }

        public Criteria andIsPayIsNull() {
            addCriterion("is_pay is null");
            return (Criteria) this;
        }

        public Criteria andIsPayIsNotNull() {
            addCriterion("is_pay is not null");
            return (Criteria) this;
        }

        public Criteria andIsPayEqualTo(Integer value) {
            addCriterion("is_pay =", value, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayNotEqualTo(Integer value) {
            addCriterion("is_pay <>", value, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayGreaterThan(Integer value) {
            addCriterion("is_pay >", value, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_pay >=", value, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayLessThan(Integer value) {
            addCriterion("is_pay <", value, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayLessThanOrEqualTo(Integer value) {
            addCriterion("is_pay <=", value, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayIn(List<Integer> values) {
            addCriterion("is_pay in", values, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayNotIn(List<Integer> values) {
            addCriterion("is_pay not in", values, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayBetween(Integer value1, Integer value2) {
            addCriterion("is_pay between", value1, value2, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayNotBetween(Integer value1, Integer value2) {
            addCriterion("is_pay not between", value1, value2, "isPay");
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

        public Criteria andRepairExpressIsNull() {
            addCriterion("repair_express is null");
            return (Criteria) this;
        }

        public Criteria andRepairExpressIsNotNull() {
            addCriterion("repair_express is not null");
            return (Criteria) this;
        }

        public Criteria andRepairExpressEqualTo(String value) {
            addCriterion("repair_express =", value, "repairExpress");
            return (Criteria) this;
        }

        public Criteria andRepairExpressNotEqualTo(String value) {
            addCriterion("repair_express <>", value, "repairExpress");
            return (Criteria) this;
        }

        public Criteria andRepairExpressGreaterThan(String value) {
            addCriterion("repair_express >", value, "repairExpress");
            return (Criteria) this;
        }

        public Criteria andRepairExpressGreaterThanOrEqualTo(String value) {
            addCriterion("repair_express >=", value, "repairExpress");
            return (Criteria) this;
        }

        public Criteria andRepairExpressLessThan(String value) {
            addCriterion("repair_express <", value, "repairExpress");
            return (Criteria) this;
        }

        public Criteria andRepairExpressLessThanOrEqualTo(String value) {
            addCriterion("repair_express <=", value, "repairExpress");
            return (Criteria) this;
        }

        public Criteria andRepairExpressLike(String value) {
            addCriterion("repair_express like", value, "repairExpress");
            return (Criteria) this;
        }

        public Criteria andRepairExpressNotLike(String value) {
            addCriterion("repair_express not like", value, "repairExpress");
            return (Criteria) this;
        }

        public Criteria andRepairExpressIn(List<String> values) {
            addCriterion("repair_express in", values, "repairExpress");
            return (Criteria) this;
        }

        public Criteria andRepairExpressNotIn(List<String> values) {
            addCriterion("repair_express not in", values, "repairExpress");
            return (Criteria) this;
        }

        public Criteria andRepairExpressBetween(String value1, String value2) {
            addCriterion("repair_express between", value1, value2, "repairExpress");
            return (Criteria) this;
        }

        public Criteria andRepairExpressNotBetween(String value1, String value2) {
            addCriterion("repair_express not between", value1, value2, "repairExpress");
            return (Criteria) this;
        }

        public Criteria andRepairExpressNumberIsNull() {
            addCriterion("repair_express_number is null");
            return (Criteria) this;
        }

        public Criteria andRepairExpressNumberIsNotNull() {
            addCriterion("repair_express_number is not null");
            return (Criteria) this;
        }

        public Criteria andRepairExpressNumberEqualTo(String value) {
            addCriterion("repair_express_number =", value, "repairExpressNumber");
            return (Criteria) this;
        }

        public Criteria andRepairExpressNumberNotEqualTo(String value) {
            addCriterion("repair_express_number <>", value, "repairExpressNumber");
            return (Criteria) this;
        }

        public Criteria andRepairExpressNumberGreaterThan(String value) {
            addCriterion("repair_express_number >", value, "repairExpressNumber");
            return (Criteria) this;
        }

        public Criteria andRepairExpressNumberGreaterThanOrEqualTo(String value) {
            addCriterion("repair_express_number >=", value, "repairExpressNumber");
            return (Criteria) this;
        }

        public Criteria andRepairExpressNumberLessThan(String value) {
            addCriterion("repair_express_number <", value, "repairExpressNumber");
            return (Criteria) this;
        }

        public Criteria andRepairExpressNumberLessThanOrEqualTo(String value) {
            addCriterion("repair_express_number <=", value, "repairExpressNumber");
            return (Criteria) this;
        }

        public Criteria andRepairExpressNumberLike(String value) {
            addCriterion("repair_express_number like", value, "repairExpressNumber");
            return (Criteria) this;
        }

        public Criteria andRepairExpressNumberNotLike(String value) {
            addCriterion("repair_express_number not like", value, "repairExpressNumber");
            return (Criteria) this;
        }

        public Criteria andRepairExpressNumberIn(List<String> values) {
            addCriterion("repair_express_number in", values, "repairExpressNumber");
            return (Criteria) this;
        }

        public Criteria andRepairExpressNumberNotIn(List<String> values) {
            addCriterion("repair_express_number not in", values, "repairExpressNumber");
            return (Criteria) this;
        }

        public Criteria andRepairExpressNumberBetween(String value1, String value2) {
            addCriterion("repair_express_number between", value1, value2, "repairExpressNumber");
            return (Criteria) this;
        }

        public Criteria andRepairExpressNumberNotBetween(String value1, String value2) {
            addCriterion("repair_express_number not between", value1, value2, "repairExpressNumber");
            return (Criteria) this;
        }

        public Criteria andTakeNameIsNull() {
            addCriterion("take_name is null");
            return (Criteria) this;
        }

        public Criteria andTakeNameIsNotNull() {
            addCriterion("take_name is not null");
            return (Criteria) this;
        }

        public Criteria andTakeNameEqualTo(String value) {
            addCriterion("take_name =", value, "takeName");
            return (Criteria) this;
        }

        public Criteria andTakeNameNotEqualTo(String value) {
            addCriterion("take_name <>", value, "takeName");
            return (Criteria) this;
        }

        public Criteria andTakeNameGreaterThan(String value) {
            addCriterion("take_name >", value, "takeName");
            return (Criteria) this;
        }

        public Criteria andTakeNameGreaterThanOrEqualTo(String value) {
            addCriterion("take_name >=", value, "takeName");
            return (Criteria) this;
        }

        public Criteria andTakeNameLessThan(String value) {
            addCriterion("take_name <", value, "takeName");
            return (Criteria) this;
        }

        public Criteria andTakeNameLessThanOrEqualTo(String value) {
            addCriterion("take_name <=", value, "takeName");
            return (Criteria) this;
        }

        public Criteria andTakeNameLike(String value) {
            addCriterion("take_name like", value, "takeName");
            return (Criteria) this;
        }

        public Criteria andTakeNameNotLike(String value) {
            addCriterion("take_name not like", value, "takeName");
            return (Criteria) this;
        }

        public Criteria andTakeNameIn(List<String> values) {
            addCriterion("take_name in", values, "takeName");
            return (Criteria) this;
        }

        public Criteria andTakeNameNotIn(List<String> values) {
            addCriterion("take_name not in", values, "takeName");
            return (Criteria) this;
        }

        public Criteria andTakeNameBetween(String value1, String value2) {
            addCriterion("take_name between", value1, value2, "takeName");
            return (Criteria) this;
        }

        public Criteria andTakeNameNotBetween(String value1, String value2) {
            addCriterion("take_name not between", value1, value2, "takeName");
            return (Criteria) this;
        }

        public Criteria andTakeTelephoneIsNull() {
            addCriterion("take_telephone is null");
            return (Criteria) this;
        }

        public Criteria andTakeTelephoneIsNotNull() {
            addCriterion("take_telephone is not null");
            return (Criteria) this;
        }

        public Criteria andTakeTelephoneEqualTo(String value) {
            addCriterion("take_telephone =", value, "takeTelephone");
            return (Criteria) this;
        }

        public Criteria andTakeTelephoneNotEqualTo(String value) {
            addCriterion("take_telephone <>", value, "takeTelephone");
            return (Criteria) this;
        }

        public Criteria andTakeTelephoneGreaterThan(String value) {
            addCriterion("take_telephone >", value, "takeTelephone");
            return (Criteria) this;
        }

        public Criteria andTakeTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("take_telephone >=", value, "takeTelephone");
            return (Criteria) this;
        }

        public Criteria andTakeTelephoneLessThan(String value) {
            addCriterion("take_telephone <", value, "takeTelephone");
            return (Criteria) this;
        }

        public Criteria andTakeTelephoneLessThanOrEqualTo(String value) {
            addCriterion("take_telephone <=", value, "takeTelephone");
            return (Criteria) this;
        }

        public Criteria andTakeTelephoneLike(String value) {
            addCriterion("take_telephone like", value, "takeTelephone");
            return (Criteria) this;
        }

        public Criteria andTakeTelephoneNotLike(String value) {
            addCriterion("take_telephone not like", value, "takeTelephone");
            return (Criteria) this;
        }

        public Criteria andTakeTelephoneIn(List<String> values) {
            addCriterion("take_telephone in", values, "takeTelephone");
            return (Criteria) this;
        }

        public Criteria andTakeTelephoneNotIn(List<String> values) {
            addCriterion("take_telephone not in", values, "takeTelephone");
            return (Criteria) this;
        }

        public Criteria andTakeTelephoneBetween(String value1, String value2) {
            addCriterion("take_telephone between", value1, value2, "takeTelephone");
            return (Criteria) this;
        }

        public Criteria andTakeTelephoneNotBetween(String value1, String value2) {
            addCriterion("take_telephone not between", value1, value2, "takeTelephone");
            return (Criteria) this;
        }

        public Criteria andTakeAreaIsNull() {
            addCriterion("take_area is null");
            return (Criteria) this;
        }

        public Criteria andTakeAreaIsNotNull() {
            addCriterion("take_area is not null");
            return (Criteria) this;
        }

        public Criteria andTakeAreaEqualTo(String value) {
            addCriterion("take_area =", value, "takeArea");
            return (Criteria) this;
        }

        public Criteria andTakeAreaNotEqualTo(String value) {
            addCriterion("take_area <>", value, "takeArea");
            return (Criteria) this;
        }

        public Criteria andTakeAreaGreaterThan(String value) {
            addCriterion("take_area >", value, "takeArea");
            return (Criteria) this;
        }

        public Criteria andTakeAreaGreaterThanOrEqualTo(String value) {
            addCriterion("take_area >=", value, "takeArea");
            return (Criteria) this;
        }

        public Criteria andTakeAreaLessThan(String value) {
            addCriterion("take_area <", value, "takeArea");
            return (Criteria) this;
        }

        public Criteria andTakeAreaLessThanOrEqualTo(String value) {
            addCriterion("take_area <=", value, "takeArea");
            return (Criteria) this;
        }

        public Criteria andTakeAreaLike(String value) {
            addCriterion("take_area like", value, "takeArea");
            return (Criteria) this;
        }

        public Criteria andTakeAreaNotLike(String value) {
            addCriterion("take_area not like", value, "takeArea");
            return (Criteria) this;
        }

        public Criteria andTakeAreaIn(List<String> values) {
            addCriterion("take_area in", values, "takeArea");
            return (Criteria) this;
        }

        public Criteria andTakeAreaNotIn(List<String> values) {
            addCriterion("take_area not in", values, "takeArea");
            return (Criteria) this;
        }

        public Criteria andTakeAreaBetween(String value1, String value2) {
            addCriterion("take_area between", value1, value2, "takeArea");
            return (Criteria) this;
        }

        public Criteria andTakeAreaNotBetween(String value1, String value2) {
            addCriterion("take_area not between", value1, value2, "takeArea");
            return (Criteria) this;
        }

        public Criteria andTakeAddressIsNull() {
            addCriterion("take_address is null");
            return (Criteria) this;
        }

        public Criteria andTakeAddressIsNotNull() {
            addCriterion("take_address is not null");
            return (Criteria) this;
        }

        public Criteria andTakeAddressEqualTo(String value) {
            addCriterion("take_address =", value, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressNotEqualTo(String value) {
            addCriterion("take_address <>", value, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressGreaterThan(String value) {
            addCriterion("take_address >", value, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressGreaterThanOrEqualTo(String value) {
            addCriterion("take_address >=", value, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressLessThan(String value) {
            addCriterion("take_address <", value, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressLessThanOrEqualTo(String value) {
            addCriterion("take_address <=", value, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressLike(String value) {
            addCriterion("take_address like", value, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressNotLike(String value) {
            addCriterion("take_address not like", value, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressIn(List<String> values) {
            addCriterion("take_address in", values, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressNotIn(List<String> values) {
            addCriterion("take_address not in", values, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressBetween(String value1, String value2) {
            addCriterion("take_address between", value1, value2, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressNotBetween(String value1, String value2) {
            addCriterion("take_address not between", value1, value2, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andOrdernumberIsNull() {
            addCriterion("ordernumber is null");
            return (Criteria) this;
        }

        public Criteria andOrdernumberIsNotNull() {
            addCriterion("ordernumber is not null");
            return (Criteria) this;
        }

        public Criteria andOrdernumberEqualTo(String value) {
            addCriterion("ordernumber =", value, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberNotEqualTo(String value) {
            addCriterion("ordernumber <>", value, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberGreaterThan(String value) {
            addCriterion("ordernumber >", value, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberGreaterThanOrEqualTo(String value) {
            addCriterion("ordernumber >=", value, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberLessThan(String value) {
            addCriterion("ordernumber <", value, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberLessThanOrEqualTo(String value) {
            addCriterion("ordernumber <=", value, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberLike(String value) {
            addCriterion("ordernumber like", value, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberNotLike(String value) {
            addCriterion("ordernumber not like", value, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberIn(List<String> values) {
            addCriterion("ordernumber in", values, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberNotIn(List<String> values) {
            addCriterion("ordernumber not in", values, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberBetween(String value1, String value2) {
            addCriterion("ordernumber between", value1, value2, "ordernumber");
            return (Criteria) this;
        }

        public Criteria andOrdernumberNotBetween(String value1, String value2) {
            addCriterion("ordernumber not between", value1, value2, "ordernumber");
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