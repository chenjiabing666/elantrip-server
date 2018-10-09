package com.yilan.elantrip.domain.rs;

import java.util.Date;
import java.util.List;

import com.yilan.elantrip.domain.NewsImage;

public class RSnewsImage {

    private String newsTitle;

    private Integer newsType;

    private String newsConent;

    private String adminName;

    private Integer activated;

    private Date createdDate;
    
    private List<NewsImage> newsImages;


    public List<NewsImage> getNewsImages() {
		return newsImages;
	}

	public void setNewsImages(List<NewsImage> newsImages) {
		this.newsImages = newsImages;
	}

	public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle == null ? null : newsTitle.trim();
    }

    public Integer getNewsType() {
        return newsType;
    }

    public void setNewsType(Integer newsType) {
        this.newsType = newsType;
    }

    public String getNewsConent() {
        return newsConent;
    }

    public void setNewsConent(String newsConent) {
        this.newsConent = newsConent == null ? null : newsConent.trim();
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public Integer getActivated() {
        return activated;
    }

    public void setActivated(Integer activated) {
        this.activated = activated;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}