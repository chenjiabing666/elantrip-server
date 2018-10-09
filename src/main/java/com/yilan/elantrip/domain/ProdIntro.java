package com.yilan.elantrip.domain;

import java.util.Date;

public class ProdIntro {
    private Integer introId;

    private Integer prodId;

    private String tripSum;

    private String tripDetails;

    private Integer days;

    private String views;

    private String breakfast;

    private String lunch;

    private String dinner;

    private Integer trafficType;

    private String trafficOther;

    private String hotels;

    private Integer activated;

    private Integer deleted;

    private Date createdDate;

    private Date updatedDate;

    public Integer getIntroId() {
        return introId;
    }

    public void setIntroId(Integer introId) {
        this.introId = introId;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getTripSum() {
        return tripSum;
    }

    public void setTripSum(String tripSum) {
        this.tripSum = tripSum == null ? null : tripSum.trim();
    }

    public String getTripDetails() {
        return tripDetails;
    }

    public void setTripDetails(String tripDetails) {
        this.tripDetails = tripDetails == null ? null : tripDetails.trim();
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views == null ? null : views.trim();
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast == null ? null : breakfast.trim();
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch == null ? null : lunch.trim();
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner == null ? null : dinner.trim();
    }

    public Integer getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(Integer trafficType) {
        this.trafficType = trafficType;
    }

    public String getTrafficOther() {
        return trafficOther;
    }

    public void setTrafficOther(String trafficOther) {
        this.trafficOther = trafficOther == null ? null : trafficOther.trim();
    }

    public String getHotels() {
        return hotels;
    }

    public void setHotels(String hotels) {
        this.hotels = hotels == null ? null : hotels.trim();
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}