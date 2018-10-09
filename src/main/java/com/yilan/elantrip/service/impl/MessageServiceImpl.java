package com.yilan.elantrip.service.impl;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.yilan.elantrip.dao.MessageImageMapper;
import com.yilan.elantrip.dao.MessageMapper;
import com.yilan.elantrip.dao.UserMapper;
import com.yilan.elantrip.dao.UserMessageMapper;
import com.yilan.elantrip.domain.Message;
import com.yilan.elantrip.domain.MessageImage;
import com.yilan.elantrip.domain.User;
import com.yilan.elantrip.domain.UserMessage;
import com.yilan.elantrip.domain.rs.MessageCount;
import com.yilan.elantrip.domain.rs.RSmessage;
import com.yilan.elantrip.service.MessageService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;
import com.yilan.elantrip.util.SendSmsUtil;

@Service
public class MessageServiceImpl implements MessageService {

	@Resource
	private MessageMapper messageMapper; // 注入dao
	@Resource
	private MessageImageMapper messageImageMapper;
	@Resource
	private UserMessageMapper userMessageMapper;
	@Resource
	private UserMapper userMapper;

	@Override
	public Object getMessage(Integer messageId) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		Message message = messageMapper.selectMessageDetail(messageId);
		List<String> images = messageMapper.getMessageImage(messageId);
		if (message == null) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("消息不存在");
			return resultInfo;
		}
		String userTypes = message.getUserType();
		if (Pattern.compile("(?i)[a-z]").matcher(userTypes).find() == false) {
			String string = userTypes.replace("0", "所有用户");
           String string1 = string.replace("1", "订单用户");
           String string2 = string1.replace("2", "非订单用户");
           String string3 = string2.replace("3", "渠道用户");
           String string4= string3.replace("4", "非渠道用户");
           String string5 = string4.replace("5", "僵尸用户");
           message.setUserType(string5);
		}
		Map<Object, Object> messageDetail = new HashMap<>();
		messageDetail.put("message", message);
		messageDetail.put("image", images);
		resultInfo.setCode("0");
		resultInfo.setMessage("获取成功");
		resultInfo.setResult(messageDetail);
		return resultInfo;
	}

	@Override
	public Object addMessage(Message message, List<MessageImage> messageImages, String userNames, String userType)
			throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		// 插入消息
		// 如果是根据类型发送，则设置user_type为类型，否则为用户名
		if (StringUtils.isEmpty(userType)) {
			message.setUserType(userNames);
		} else {
			message.setUserType(userType);
		}
		int count = messageMapper.insertSelective(message);
		if (count == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加失败");
			return resultInfo;
		}
		// 插入图片
		for (MessageImage messageImage : messageImages) {
			messageImage.setMessageId(message.getMessageId());
			messageImage.setCreatedDate(new Date());
			int count1 = messageImageMapper.insertSelective(messageImage);
			if (count1 == 0) {
				resultInfo.setCode("-1");
				resultInfo.setMessage("添加图片失败");
				return resultInfo;
			}
		}
		// 插入用户消息
		ArrayList<UserMessage> usermessages = new ArrayList<UserMessage>();
		List<User> users = null;
		// userType不为空表示通过类型查找用户，否则通过用户名查找
		if (!StringUtils.isEmpty(userType)) {
			// 类型为0时表示查找所有用户
			if (message.getUserType().equals("0")) { 
				users = userMapper.getUserListByUserTempType("1,2,3,4,5");
			} else {
				users = userMapper.getUserListByUserTempType(message.getUserType());			
			}
		} else {
			users = userMapper.getUserListByUserNames(userNames);
			List<String> userNames1 = Arrays.asList(userNames.split(","));
			if (userNames1.size() != users.size()) {
				resultInfo.setCode("-1");
				resultInfo.setMessage("用户名有误，请检查用户名是否输入正确");
				throw new ApplicationContextException("添加消息失败");
			}
		}
		if (users.size() == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("用户数量为0");
			throw new ApplicationContextException("添加消息失败");
		}
		for (User user : users) {
			UserMessage userMessage = new UserMessage();
			userMessage.setToUserId(user.getUserId());
			userMessage.setMessageId(message.getMessageId());
			userMessage.setCreatedDatetime(new Date());
			usermessages.add(userMessage);
		}
		int count2 = userMessageMapper.insertBatchUserMessage(usermessages);
		if (count2 == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加用户消息失败");
			throw new ApplicationContextException("添加用户消息失败");
		}
		// 发送短信
		if (message.getMessageTypeId() == 4 || message.getMessageTypeId() == 5 || message.getMessageTypeId() == 6) {
			// 查找需要发送短信的用户的电话号码
			List<String> mobileList = new ArrayList<>();
			for (User user : users) {
				if (user.getMobile() == null) {
					resultInfo.setCode("-1");
					resultInfo.setMessage("用户id为" + user.getUserId() + "的手机号为空");
					return resultInfo;
				}
				mobileList.add(user.getMobile());
			}
			String mobiles = String.join(",", mobileList);
			System.out.println(mobiles);
			try {
				SendSmsUtil.sendCodeByModifyPassword(mobiles, message.getMessageContent()); // 发送
			} catch (Exception e) {
				resultInfo.setMessage("短信发送异常");
				resultInfo.setCode("-1");
				throw new ApplicationContextException("短信发送失败");
			}
		}
		resultInfo.setCode("0");
		resultInfo.setMessage("添加成功");
		return resultInfo;
	}

	@Override
	public Object deleteMessage(Integer messageId) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = messageMapper.deleteByPrimaryKey(messageId);
		if (count == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("删除消息失败");
			return resultInfo;
		}
		int count1 = messageImageMapper.deleteByPrimaryKey(messageId);
		if (count1 == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("删除消息图片失败");
			return resultInfo;
		}
		resultInfo.setMessage("删除成功");
		return resultInfo;
	}

	@Override
	public Object countTotal(PagingTool pagingTool) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = messageMapper.countTotal(pagingTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取总数异常");
		}
		return count;
	}

	@Override
	public List<RSmessage> getMessageList(PagingTool pagingTool) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		List<RSmessage> messageList = null;
		try {
			MessageCount[] messageCounts = messageMapper.getMessageCountGroupByMessageId();
			messageList = messageMapper.selectMessageList(pagingTool);
			for (MessageCount messageCount : messageCounts) {
				for (RSmessage rSmessage : messageList) {
					if (rSmessage.getMessageId() == messageCount.getMessageId()) {
						rSmessage.setSendCount(messageCount.getCount());
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取消息列表异常");
		}

		return messageList;
	}

	@Override
	public int getUserMessageCountTotal(PagingTool pageTool) {
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = messageMapper.getUserMessageCountTotal(pageTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取总数异常");
		}
		return count;
	}

	@Override
	public List<Message> getUserMessageList(PagingTool pageTool) {
		ResultInfo resultInfo = new ResultInfo();
		List<Message> messages = null;
		try {
			messages = messageMapper.selectUserMessageList(pageTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取用户消息列表异常");
		}
		return messages;
	}
}
