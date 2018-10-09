package com.yilan.elantrip.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yilan.elantrip.domain.Message;
import com.yilan.elantrip.domain.MessageImage;
import com.yilan.elantrip.domain.rs.RSmessage;
import com.yilan.elantrip.util.PagingTool;

/**
 * 消息的Service
 * 
 * @author Administrator
 *
 */
@Transactional // 开启事务注解,默认运行时异常事务回滚
public interface MessageService {

	/**
	 * 获取消息详细信息
	 * 
	 * @param messageId
	 *            消息Id
	 * @return
	 * @throws Exception
	 */
	Object getMessage(Integer messageId) throws Exception;

	/**
	 * 添加消息
	 * 
	 * @param message
	 *            消息 messageImages 消息图片
	 * @return
	 * @throws Exception
	 */
	Object addMessage(Message message, List<MessageImage> messageImages,String userNames,String userType) throws Exception;

	/**
	 * 删除消息
	 * 
	 * @param messageId
	 *            消息ID
	 * @return
	 * @throws Exception
	 */
	Object deleteMessage(Integer messageId) throws Exception;

	/**
	 * 获取消息列表
	 * 
	 * @param PagingTool
	 * @return
	 * @throws Exception
	 */
	List<RSmessage> getMessageList(PagingTool pagingTool) throws Exception;

	/**
	 * 获取消息总数
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Object countTotal(PagingTool pagingTool) throws Exception;

	int getUserMessageCountTotal(PagingTool pageTool);

	List<Message> getUserMessageList(PagingTool pageTool);

}
