package com.yilan.elantrip.domain;

import java.util.Date;

public class BustripImage {
    private Integer bustripImageId;

    private Integer bustripId;

    private String imageUrl;

    private Integer activated;

    private Integer deleted;

    private Date createdDatetime;

    private Date updatedDatetime;

    public Integer getBustripImageId() {
        return bustripImageId;
    }

    public void setBustripImageId(Integer bustripImageId) {
        this.bustripImageId = bustripImageId;
    }

    public Integer getBustripId() {
        return bustripId;
    }

    public void setBustripId(Integer bustripId) {
        this.bustripId = bustripId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
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
}