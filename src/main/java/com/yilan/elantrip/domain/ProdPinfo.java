package com.yilan.elantrip.domain;

import java.util.Date;

public class ProdPinfo {
    private Integer pinfoId;

    private Integer prodId;

    private Integer type;

    private Integer ageUp;

    private Integer ageDown;

    private Integer ageIsUp;

    private Integer ageIsDown;

    private Integer heightDown;

    private Integer heightUp;

    private Integer hIsUp;

    private Integer hIsDown;

    private String remark;

    private Integer isJoin;

    private Integer isAlone;

    private Integer isFree;

    private Integer isInclTicket;

    private String specialRemark;

    private Integer isInclCar;

    private String carMoney;

    private String projectType;

    private String notProName;

    private String notJoinProName;

    private String onlyProName;

    private Integer onlyAge;

    private Integer activated;

    private Integer deleted;

    private Date updatedDate;

    private Date createdDate;

    public Integer getPinfoId() {
        return pinfoId;
    }

    public void setPinfoId(Integer pinfoId) {
        this.pinfoId = pinfoId;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAgeUp() {
        return ageUp;
    }

    public void setAgeUp(Integer ageUp) {
        this.ageUp = ageUp;
    }

    public Integer getAgeDown() {
        return ageDown;
    }

    public void setAgeDown(Integer ageDown) {
        this.ageDown = ageDown;
    }

    public Integer getAgeIsUp() {
        return ageIsUp;
    }

    public void setAgeIsUp(Integer ageIsUp) {
        this.ageIsUp = ageIsUp;
    }

    public Integer getAgeIsDown() {
        return ageIsDown;
    }

    public void setAgeIsDown(Integer ageIsDown) {
        this.ageIsDown = ageIsDown;
    }

    public Integer getHeightDown() {
        return heightDown;
    }

    public void setHeightDown(Integer heightDown) {
        this.heightDown = heightDown;
    }

    public Integer getHeightUp() {
        return heightUp;
    }

    public void setHeightUp(Integer heightUp) {
        this.heightUp = heightUp;
    }

    public Integer gethIsUp() {
        return hIsUp;
    }

    public void sethIsUp(Integer hIsUp) {
        this.hIsUp = hIsUp;
    }

    public Integer gethIsDown() {
        return hIsDown;
    }

    public void sethIsDown(Integer hIsDown) {
        this.hIsDown = hIsDown;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIsJoin() {
        return isJoin;
    }

    public void setIsJoin(Integer isJoin) {
        this.isJoin = isJoin;
    }

    public Integer getIsAlone() {
        return isAlone;
    }

    public void setIsAlone(Integer isAlone) {
        this.isAlone = isAlone;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public Integer getIsInclTicket() {
        return isInclTicket;
    }

    public void setIsInclTicket(Integer isInclTicket) {
        this.isInclTicket = isInclTicket;
    }

    public String getSpecialRemark() {
        return specialRemark;
    }

    public void setSpecialRemark(String specialRemark) {
        this.specialRemark = specialRemark == null ? null : specialRemark.trim();
    }

    public Integer getIsInclCar() {
        return isInclCar;
    }

    public void setIsInclCar(Integer isInclCar) {
        this.isInclCar = isInclCar;
    }

    public String getCarMoney() {
        return carMoney;
    }

    public void setCarMoney(String carMoney) {
        this.carMoney = carMoney == null ? null : carMoney.trim();
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType == null ? null : projectType.trim();
    }

    public String getNotProName() {
        return notProName;
    }

    public void setNotProName(String notProName) {
        this.notProName = notProName == null ? null : notProName.trim();
    }

    public String getNotJoinProName() {
        return notJoinProName;
    }

    public void setNotJoinProName(String notJoinProName) {
        this.notJoinProName = notJoinProName == null ? null : notJoinProName.trim();
    }

    public String getOnlyProName() {
        return onlyProName;
    }

    public void setOnlyProName(String onlyProName) {
        this.onlyProName = onlyProName == null ? null : onlyProName.trim();
    }

    public Integer getOnlyAge() {
        return onlyAge;
    }

    public void setOnlyAge(Integer onlyAge) {
        this.onlyAge = onlyAge;
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