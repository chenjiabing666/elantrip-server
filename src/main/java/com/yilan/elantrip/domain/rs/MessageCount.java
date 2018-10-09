package com.yilan.elantrip.domain.rs;

public class MessageCount {
     private int messageId;
     private int count;
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "MessageCount [messageId=" + messageId + ", count=" + count + "]";
	}
     
}
