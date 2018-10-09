package com.yilan.elantrip.domain;

import java.util.Date;

public class Comment {
    private Integer commentId;

    private Integer tripType;

    private Integer prodId;

    private Integer orderId;

    private String productName;

    private String allReview;

    private Integer grade;

    private Integer userId;

    private String trafficContent;

    private Integer trafficGrade;

    private String hotelContent;

    private Integer hotelGrade;

    private String guideContent;

    private Integer guideGrade;

    private String foodContent;

    private Integer foodGrade;

    private Integer prodGrade;

    private String prodContent;

    private Integer tripGrade;

    private String tripContent;

    private String reply;

    private Integer activated;

    private Integer deleted;

    private Date createdDatetime;

    private Date updatedDatetime;

    private Integer advicePeople;

    private Integer adviceSeason;

    private Integer adviceTime;

    private Integer advicePreferred;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getTripType() {
        return tripType;
    }

    public void setTripType(Integer tripType) {
        this.tripType = tripType;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getAllReview() {
        return allReview;
    }

    public void setAllReview(String allReview) {
        this.allReview = allReview == null ? null : allReview.trim();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTrafficContent() {
        return trafficContent;
    }

    public void setTrafficContent(String trafficContent) {
        this.trafficContent = trafficContent == null ? null : trafficContent.trim();
    }

    public Integer getTrafficGrade() {
        return trafficGrade;
    }

    public void setTrafficGrade(Integer trafficGrade) {
        this.trafficGrade = trafficGrade;
    }

    public String getHotelContent() {
        return hotelContent;
    }

    public void setHotelContent(String hotelContent) {
        this.hotelContent = hotelContent == null ? null : hotelContent.trim();
    }

    public Integer getHotelGrade() {
        return hotelGrade;
    }

    public void setHotelGrade(Integer hotelGrade) {
        this.hotelGrade = hotelGrade;
    }

    public String getGuideContent() {
        return guideContent;
    }

    public void setGuideContent(String guideContent) {
        this.guideContent = guideContent == null ? null : guideContent.trim();
    }

    public Integer getGuideGrade() {
        return guideGrade;
    }

    public void setGuideGrade(Integer guideGrade) {
        this.guideGrade = guideGrade;
    }

    public String getFoodContent() {
        return foodContent;
    }

    public void setFoodContent(String foodContent) {
        this.foodContent = foodContent == null ? null : foodContent.trim();
    }

    public Integer getFoodGrade() {
        return foodGrade;
    }

    public void setFoodGrade(Integer foodGrade) {
        this.foodGrade = foodGrade;
    }

    public Integer getProdGrade() {
        return prodGrade;
    }

    public void setProdGrade(Integer prodGrade) {
        this.prodGrade = prodGrade;
    }

    public String getProdContent() {
        return prodContent;
    }

    public void setProdContent(String prodContent) {
        this.prodContent = prodContent == null ? null : prodContent.trim();
    }

    public Integer getTripGrade() {
        return tripGrade;
    }

    public void setTripGrade(Integer tripGrade) {
        this.tripGrade = tripGrade;
    }

    public String getTripContent() {
        return tripContent;
    }

    public void setTripContent(String tripContent) {
        this.tripContent = tripContent == null ? null : tripContent.trim();
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
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

    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public Date getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(Date updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }

    public Integer getAdvicePeople() {
        return advicePeople;
    }

    public void setAdvicePeople(Integer advicePeople) {
        this.advicePeople = advicePeople;
    }

    public Integer getAdviceSeason() {
        return adviceSeason;
    }

    public void setAdviceSeason(Integer adviceSeason) {
        this.adviceSeason = adviceSeason;
    }

    public Integer getAdviceTime() {
        return adviceTime;
    }

    public void setAdviceTime(Integer adviceTime) {
        this.adviceTime = adviceTime;
    }

    public Integer getAdvicePreferred() {
        return advicePreferred;
    }

    public void setAdvicePreferred(Integer advicePreferred) {
        this.advicePreferred = advicePreferred;
    }
}