package com.yilan.elantrip.domain.rs;

import java.util.Date;
/**
 * 用于消息列表
 * @author Administrator
 */
public class RSmessage {
	private Integer messageId; // 消息id

    private Integer typeId; // 消息类型

    private String userClassifyName; // 用户类型名

    private String messageContent; // 消息内容

    private Date createdDatetime; // 发送时间
    
    private String fromUser; // 发送人
    
    private Integer sendCount; // 发送条数
    
	@Override
	public String toString() {
		return "RSmessage [messageId=" + messageId + ", typeId=" + typeId + ", userClassifyName=" + userClassifyName
				+ ", messageContent=" + messageContent + ", createdDatetime=" + createdDatetime + ", fromUser="
				+ fromUser + ", sendCount=" + sendCount + "]";
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public Integer getSendCount() {
		return sendCount;
	}

	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
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
    

}
