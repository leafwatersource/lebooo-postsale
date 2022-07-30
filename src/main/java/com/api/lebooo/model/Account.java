package com.api.lebooo.model;

import java.util.Date;

public class Account {
    private Long id;

    private String accountName;

    private String password;

    private String token;

    /**
     * 姓名
     */
    private String nickname;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 盐
     */
    private String salt;

    /**
     * 最近登录时间
     */
    private Date newLogin;

    /**
     * 0 管理,1 客服,2 仓管
     */
    private Integer accountType;

    /**
     * 头像
     */
    private String img;

    /**
     * 0 启用，1 停用
     */
    private Integer isStatus;

    private Integer isDel;

    /**
     * 修改者
     */
    private Long updateAccountId;

    /**
     * 修改者时间
     */
    private Date updateTime;

    /**
     * 创建者
     */
    private Long createAccountId;

    /**
     * 创建者时间
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Date getNewLogin() {
        return newLogin;
    }

    public void setNewLogin(Date newLogin) {
        this.newLogin = newLogin;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(Integer isStatus) {
        this.isStatus = isStatus;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Long getUpdateAccountId() {
        return updateAccountId;
    }

    public void setUpdateAccountId(Long updateAccountId) {
        this.updateAccountId = updateAccountId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateAccountId() {
        return createAccountId;
    }

    public void setCreateAccountId(Long createAccountId) {
        this.createAccountId = createAccountId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}