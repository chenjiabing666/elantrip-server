package com.yilan.elantrip.domain.rs;


import java.util.Date;
import java.util.List;

import com.yilan.elantrip.domain.MessageImage;
/**
 * 用于消息详情
 * @author Administrator
 */
public class RSmessageImage {
	private Integer messageId; // 消息id

    private Integer typeId; // 消息类型

    private String userClassifyName; // 用户类型名

    private String messageContent; // 消息内容

    private Date createdDatetime; // 创建时间
    
    private String fromUser; // 发送人
    
    private List<MessageImage> messageImages; // 消息图片

	public List<MessageImage> getMessageImages() {
		return messageImages;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public void setMessageImages(List<MessageImage> messageImages) {
		this.messageImages = messageImages;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getUserClassifyName() {
		return userClassifyName;
	}

	public void setUserClassifyName(String userClassifyName) {
		this.userClassifyName = userClassifyName;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	@Override
	public String toString() {
		return "RSmessageImage [messageId=" + messageId + ", typeName=" + typeId + ", userClassifyName="
				+ userClassifyName + ", messageContent=" + messageContent + ", createdDatetime=" + createdDatetime
				+ ", messageImages=" + messageImages + "]";
	}
    

}
