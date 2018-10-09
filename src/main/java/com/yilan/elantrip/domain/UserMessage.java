package com.yilan.elantrip.domain;

import java.util.Date;

public class UserMessage {
    private Integer userMessageId;

    private Integer toUserId;

    private Integer messageId;

    private Integer activated;

    private Integer deleted;

    private Date createdDatetime;

    private Date updatedDatetime;

    public Integer getUserMessageId() {
        return userMessageId;
    }

    public void setUserMessageId(Integer userMessageId) {
        this.userMessageId = userMessageId;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
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

	@Override
	public String toString() {
		return "UserMessage [userMessageId=" + userMessageId + ", toUserId="
				+ toUserId + ", messageId=" + messageId + ", activated="
				+ activated + ", deleted=" + deleted + ", createdDatetime="
				+ createdDatetime + ", updatedDatetime=" + updatedDatetime
				+ "]";
	}
    
}