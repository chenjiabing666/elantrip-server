package com.yilan.elantrip.domain;

import java.util.Date;

public class Banner {
    private Integer bannerId;

    private String imageUrl;

    private String locationUrl;

    private Integer sortIndex;

    private String jumpUrl;

    private Integer location;

    private Integer bannerType;

    private String description;

    private Integer activated;

    private Integer top;

    private Date topTime;

    private Integer deleted;

    private Date createdDate;

    private Date updatedDate;

    @Override
	public String toString() {
		return "Banner [bannerId=" + bannerId + ", imageUrl=" + imageUrl + ", locationUrl=" + locationUrl
				+ ", sortIndex=" + sortIndex + ", jumpUrl=" + jumpUrl + ", location=" + location + ", bannerType="
				+ bannerType + ", description=" + description + ", activated=" + activated + ", top=" + top
				+ ", topTime=" + topTime + ", deleted=" + deleted + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + "]";
	}

	public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getLocationUrl() {
        return locationUrl;
    }

    public void setLocationUrl(String locationUrl) {
        this.locationUrl = locationUrl == null ? null : locationUrl.trim();
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl == null ? null : jumpUrl.trim();
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getBannerType() {
        return bannerType;
    }

    public void setBannerType(Integer bannerType) {
        this.bannerType = bannerType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getActivated() {
        return activated;
    }

    public void setActivated(Integer activated) {
        this.activated = activated;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Date getTopTime() {
        return topTime;
    }

    public void setTopTime(Date topTime) {
        this.topTime = topTime;
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