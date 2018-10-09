package com.yilan.elantrip.domain.rs;


import java.util.Date;
import java.util.List;

import com.yilan.elantrip.domain.FeedbackImage;
/**
 * 用于反馈详情
 * @author Administrator
 */
public class RSfeedbackImage {
	private Integer feedbackId; // 反馈id

    private String feedbackTitle; // 反馈主题

    private String feedbackContent; // 反馈内容
 
    private String userName; // 用户姓名

    private Date createdDate; // 反馈时间

    private List<FeedbackImage> feedbackImages; // 反馈图片

	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getFeedbackTitle() {
		return feedbackTitle;
	}

	public void setFeedbackTitle(String feedbackTitle) {
		this.feedbackTitle = feedbackTitle;
	}

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<FeedbackImage> getFeedbackImages() {
		return feedbackImages;
	}

	public void setFeedbackImages(List<FeedbackImage> feedbackImages) {
		this.feedbackImages = feedbackImages;
	}

	@Override
	public String toString() {
		return "RSfeedbackImage [feedbackId=" + feedbackId + ", feedbackTitle=" + feedbackTitle + ", feedbackContent="
				+ feedbackContent + ", userName=" + userName + ", createdDate=" + createdDate + ", feedbackImages="
				+ feedbackImages + "]";
	}

	

}
