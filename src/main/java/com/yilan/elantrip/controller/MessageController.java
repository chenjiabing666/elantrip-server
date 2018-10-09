package com.yilan.elantrip.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.yilan.elantrip.domain.Message;
import com.yilan.elantrip.domain.MessageImage;
import com.yilan.elantrip.domain.rs.RSmessage;
import com.yilan.elantrip.service.MessageService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;
import com.yilan.elantrip.util.UploadFileUtils;
import com.yilan.elantrip.util.WangEditorDomin;

/**
 * 消息的Controller
 * 
 * @author Administrator
 *
 */
@RestController // 直接使用@RestController，不用@ResponseBody
public class MessageController {
	@Resource
	private MessageService messageService; // 注入

	/**
	 * 获取消息/短信详情
	 * 
	 * @param messageId
	 *            消息Id
	 * @param request
	 * @return
	 */
	@RequestMapping("/message/getMessage")
	public Object getMessage(HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo();
		String messageId = request.getParameter("messageId");
		System.out.println(messageId);
		// 校验参数
		if (StringUtils.isEmpty(messageId)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("消息Id不能为空");
			return resultInfo;
		}

		// 调用service层的方法
		try {
			Object object = messageService.getMessage(Integer.parseInt(messageId));
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("获取异常");
			return resultInfo;
		}
	}

	/**
	 * 添加消息/短信
	 * 
	 * @param messageTypeId
	 *            消息类型
	 * @param userType
	 *            用户类型
	 * @param messageContent
	 *            内容
	 * @param fromUser
	 *            发送人
	 * @param messageImage
	 *            图片
	 * @param userNames
	 *            用户名
	 * @param request
	 * @return
	 */
	@RequestMapping("/message/addMessage")
	public Object addMessage(String messageTypeId, String userType, String messageContent, String fromUser,
			String userNames, @RequestParam(value = "messageImage", required = false) MultipartFile[] messageImage) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集
		WangEditorDomin wangEditorDomin = new WangEditorDomin();
		Message message = new Message();
		List<MessageImage> messageImages = new ArrayList<MessageImage>();
		System.out.println(messageTypeId);
		System.out.println(userType);
		System.out.println(messageContent);
		System.out.println(fromUser);
		System.out.println(userNames + "111111111111111111");
		System.out.println(messageImage.length);
		if (messageImage != null && messageImage.length > 5) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("最多只能上传5张图片");
			return resultInfo;
		}
		// 处理图片
		if (messageImage != null) {
			for (MultipartFile imageFile : messageImage) {
				String messageImageFileName = System.currentTimeMillis() + imageFile.getOriginalFilename();
				String imageUrl = null;
				try {
					imageUrl = UploadFileUtils.uploadFile("message-image", messageImageFileName, imageFile);
					MessageImage image = new MessageImage();
					image.setImageUrl(imageUrl);
					image.setCreatedDate(new Date());
					messageImages.add(image);
				} catch (Exception e) {
					wangEditorDomin.setErrno("-1"); // 上传失败
					return wangEditorDomin;
				}
			}
		}
		// 校验参数
		if (StringUtils.isEmpty(messageTypeId)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("消息类型id不能为空");
			return resultInfo;
		}

		if (StringUtils.isEmpty(messageContent)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("消息内容不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(fromUser)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("发送人不能为空");
			return resultInfo;
		}

		if (StringUtils.isEmpty(userType) && StringUtils.isEmpty(userNames)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("用户类型和用户名不能同时为空");
			return resultInfo;
		}

		// 封装数据

		if (!StringUtils.isEmpty(userType)) {
			message.setUserType(userType);
		}
		message.setCreatedDatetime(new Date());
		message.setFromUser(fromUser);
		message.setMessageContent(messageContent);
		message.setMessageTypeId(Integer.parseInt(messageTypeId));

		// 调用service层的方法

		try {
			Object object = messageService.addMessage(message, messageImages, userNames, userType);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加公告异常");
			return resultInfo;
		}
	}
	
	/***
	 * 获取用户消息列表
	 * @param userId 用户id
	 * @return
	 */
	@RequestMapping(value = "/message/getUserMessageList")
	public @ResponseBody Object getUserMessageList(String userId,HttpServletRequest request) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集
		HashMap<String, Object> params = new HashMap<String, Object>();
		List<Map<String, Object>> results = new ArrayList<>();
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		if (userId == null || userId.equals("")) {
			reInfo.setMessage("用户ID不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (pageNum == null || pageNum.equals("")) {
			reInfo.setMessage("页码不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (pageSize == null || pageSize.equals("")) {
			reInfo.setMessage("页大小不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		params.put("userId", userId);
		PagingTool pageTool = new PagingTool(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		pageTool.setParams(params);
		int totalCount = 0;

		try {
			totalCount = (int) messageService.getUserMessageCountTotal(pageTool);
		} catch (Exception e) {
			reInfo.setMessage("获取总数异常！");
			reInfo.setCode("-1");
			return reInfo;
		}

		if (totalCount == 0) {
			reInfo.setMessage("总数量为0！");
			reInfo.setCode("-1");
			return reInfo;
		}
		List<Message> messages = null;
		try {
			messages = messageService.getUserMessageList(pageTool);
			for (Message message : messages) {
				LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>();
				result.put("messageId", message.getMessageId());
				result.put("title", message.getTitle());
				result.put("createdDatetime", message.getCreatedDatetime());
				results.add(result);
			}
		} catch (Exception e) {
			reInfo.setMessage("获取列表异常！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (messages.size() == 0) {
			reInfo.setMessage("列表为空！");
			reInfo.setResult(new ArrayList<Message>());
			return reInfo;
		}
		reInfo.setResult(results);
		reInfo.setTotal(totalCount);
		reInfo.setCode("0");
		reInfo.setMessage("获取列表成功！");
		return reInfo;
	}
	
	/**
	 * 获取消息/短信列表
	 * 
	 * @param pageNum
	 *            起始页
	 * @param pageSize
	 *            每页显示数量
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/message/getMessageList")
	public @ResponseBody Object getNewsList(HttpServletRequest request, HttpServletResponse response) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集
		HashMap<String, Object> params = new HashMap<String, Object>();
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		if (pageNum == null || pageNum.equals("")) {
			reInfo.setMessage("页码不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (pageSize == null || pageSize.equals("")) {
			reInfo.setMessage("页大小不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		PagingTool pageTool = new PagingTool(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		pageTool.setParams(params);
		int totalCount = 0;

		try {
			totalCount = (int) messageService.countTotal(pageTool);
		} catch (Exception e) {
			reInfo.setMessage("获取总数异常！");
			reInfo.setCode("-1");
			return reInfo;
		}

		if (totalCount == 0) {
			reInfo.setMessage("总数量为0！");
			reInfo.setCode("-1");
			return reInfo;
		}
		List<RSmessage> messageList = null;
		try {
			messageList = messageService.getMessageList(pageTool);
			for (RSmessage rSmessage : messageList) {
				String userTypes = rSmessage.getUserClassifyName();
				if (Pattern.compile("(?i)[a-z]").matcher(userTypes).find() == false) {
					String string = userTypes.replace("0", "所有用户");
					String string1 = string.replace("1", "订单用户");
					String string2 = string1.replace("2", "非订单用户");
					String string3 = string2.replace("3", "渠道用户");
					String string4 = string3.replace("4", "非渠道用户");
					String string5 = string4.replace("5", "僵尸用户");
					rSmessage.setUserClassifyName(string5);
				}
			}

		} catch (Exception e) {
			reInfo.setMessage("获取列表异常！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (messageList.size() == 0) {
			reInfo.setMessage("列表为空！");
			reInfo.setResult(new ArrayList<RSmessage>());
			return reInfo;
		}
		reInfo.setCode("0");
		reInfo.setResult(messageList);
		reInfo.setTotal(totalCount);
		reInfo.setMessage("获取列表成功！");
		return reInfo;
	}
}
