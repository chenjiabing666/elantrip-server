package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.Message;
import com.yilan.elantrip.domain.rs.MessageCount;
import com.yilan.elantrip.domain.rs.RSmessage;
import com.yilan.elantrip.util.PagingTool;

public interface MessageMapper {
	int deleteByPrimaryKey(Integer messageId);

	int insert(Message record);

	int insertSelective(Message record);

	Message selectByPrimaryKey(Integer messageId);

	int updateByPrimaryKeySelective(Message record);

	int updateByPrimaryKey(Message record);

	// 查询总数
	int countTotal(PagingTool pagingTool);

	// 查询列表
	List<RSmessage> selectMessageList(PagingTool pagingTool);

	// 获取详情
	Message selectMessageDetail(Integer messageId);
	
	// 查找每条消息发送的数量
	MessageCount[] getMessageCountGroupByMessageId();
	
	// 根据消息id查找消息图片
	List<String> getMessageImage(Integer messageId);

	int getUserMessageCountTotal(PagingTool pageTool);

	List<Message> selectUserMessageList(PagingTool pageTool);
}