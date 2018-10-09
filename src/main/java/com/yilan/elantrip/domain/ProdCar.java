package com.yilan.elantrip.domain;

import java.util.Date;

public class ProdCar {
    private Integer prodCarId;

    private Integer carType;

    private Integer prodId;

    private Integer carLocation;

    private Integer perDown;

    private Integer perUp;

    private Double price;

    private Integer activated;

    private Integer deleted;

    private Date updatedDate;

    private Date createdDate;

    public Integer getProdCarId() {
        return prodCarId;
    }

    public void setProdCarId(Integer prodCarId) {
        this.prodCarId = prodCarId;
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public Integer getCarLocation() {
        return carLocation;
    }

    public void setCarLocation(Integer carLocation) {
        this.carLocation = carLocation;
    }

    public Integer getPerDown() {
        return perDown;
    }

    public void setPerDown(Integer perDown) {
        this.perDown = perDown;
    }

    public Integer getPerUp() {
        return perUp;
    }

    public void setPerUp(Integer perUp) {
        this.perUp = perUp;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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