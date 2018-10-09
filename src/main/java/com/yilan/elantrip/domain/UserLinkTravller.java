package com.yilan.elantrip.domain;

import java.util.Date;

public class UserLinkTravller {
    private Integer userLinkTravllerId;

    private Integer userId;

    private String orderCode;

    private String country;

    private String chinaName;

    private String enName;

    private String cerCode;

    private Integer cerType;

    private Date cerEndDate;

    private Integer gender;

    private Date birth;

    private String email;

    private String mobile;

    private Integer userType;

    private Integer isLink;

    private Integer isDefault;

    private Integer isOften;

    private Integer activated;

    private Integer deleted;

    private Date updatedDate;

    private Date createdDate;

    public Integer getUserLinkTravllerId() {
        return userLinkTravllerId;
    }

    public void setUserLinkTravllerId(Integer userLinkTravllerId) {
        this.userLinkTravllerId = userLinkTravllerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getChinaName() {
        return chinaName;
    }

    public void setChinaName(String chinaName) {
        this.chinaName = chinaName == null ? null : chinaName.trim();
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    public String getCerCode() {
        return cerCode;
    }

    public void setCerCode(String cerCode) {
        this.cerCode = cerCode == null ? null : cerCode.trim();
    }

    public Integer getCerType() {
        return cerType;
    }

    public void setCerType(Integer cerType) {
        this.cerType = cerType;
    }

    public Date getCerEndDate() {
        return cerEndDate;
    }

    public void setCerEndDate(Date cerEndDate) {
        this.cerEndDate = cerEndDate;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getIsLink() {
        return isLink;
    }

    public void setIsLink(Integer isLink) {
        this.isLink = isLink;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getIsOften() {
        return isOften;
    }

    public void setIsOften(Integer isOften) {
        this.isOften = isOften;
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
}