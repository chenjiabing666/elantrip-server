package com.yilan.elantrip.domain;

import java.util.Date;

public class Coupon {
    private Integer couponId;

    private Integer typeId;

    private Integer couponType;

    private String couponName;

    private Double price;

    private Integer isEffective;

    private Integer totalCount;

    private Integer couponRecieved;

    private Integer couponUsed;

    private String usersSuitable;

    private String recieveType;

    private Integer requirment;

    private Double fullMoney;

    private Integer numLimit;

    private Date launchStime;

    private Date launchEtime;

    private Integer activated;

    private Integer deleted;

    private Date updatedDate;

    private Date createdDate;

    private Date expiryStartDate;

    private Date expriyEndDate;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(Integer isEffective) {
        this.isEffective = isEffective;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getCouponRecieved() {
        return couponRecieved;
    }

    public void setCouponRecieved(Integer couponRecieved) {
        this.couponRecieved = couponRecieved;
    }

    public Integer getCouponUsed() {
        return couponUsed;
    }

    public void setCouponUsed(Integer couponUsed) {
        this.couponUsed = couponUsed;
    }

    public String getUsersSuitable() {
        return usersSuitable;
    }

    public void setUsersSuitable(String usersSuitable) {
        this.usersSuitable = usersSuitable == null ? null : usersSuitable.trim();
    }

    public String getRecieveType() {
        return recieveType;
    }

    public void setRecieveType(String recieveType) {
        this.recieveType = recieveType == null ? null : recieveType.trim();
    }

    public Integer getRequirment() {
        return requirment;
    }

    public void setRequirment(Integer requirment) {
        this.requirment = requirment;
    }

    public Double getFullMoney() {
        return fullMoney;
    }

    public void setFullMoney(Double fullMoney) {
        this.fullMoney = fullMoney;
    }

    public Integer getNumLimit() {
        return numLimit;
    }

    public void setNumLimit(Integer numLimit) {
        this.numLimit = numLimit;
    }

    public Date getLaunchStime() {
        return launchStime;
    }

    public void setLaunchStime(Date launchStime) {
        this.launchStime = launchStime;
    }

    public Date getLaunchEtime() {
        return launchEtime;
    }

    public void setLaunchEtime(Date launchEtime) {
        this.launchEtime = launchEtime;
    }

    public Integer getActivated() {
        return activated;
    }

    public void setActivated(Integer activated) {
        this.activated = activated;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getExpiryStartDate() {
        return expiryStartDate;
    }

    public void setExpiryStartDate(Date expiryStartDate) {
        this.expiryStartDate = expiryStartDate;
    }

    public Date getExpriyEndDate() {
        return expriyEndDate;
    }

    public void setExpriyEndDate(Date expriyEndDate) {
        this.expriyEndDate = expriyEndDate;
    }
}