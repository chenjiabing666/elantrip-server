package com.yilan.elantrip.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.yilan.elantrip.domain.Feedback;
import com.yilan.elantrip.domain.FeedbackImage;
import com.yilan.elantrip.service.FeedbackService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;
import com.yilan.elantrip.util.UploadFileUtils;

/**
 * 反馈的Controller
 * 
 * @author Administrator
 *
 */
@RestController // 直接使用@RestController，不用@ResponseBody
public class FeedbackController {

	@Resource
	private FeedbackService feedbackService; // 注入

	/**
	 * 获取消反馈详情
	 * 
	 * @param feedbackId
	 *            反馈Id
	 * @param request
	 * @return
	 */
	@RequestMapping("/feedback/getFeedback")
	public Object getMessage(HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo();
		String feedbackId = request.getParameter("feedbackId");
		// 校验参数
		if (StringUtils.isEmpty(feedbackId)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("反馈Id不能为空");
			return resultInfo;
		}

		// 调用service层的方法
		try {
			Object object = feedbackService.getFeedbackById(Integer.parseInt(feedbackId));
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("获取异常");
			return resultInfo;
		}
	}

	/**
	 * 添加反馈
	 * 
	 * @param feedbackTitle
	 *            反馈主题
	 * @param feedbackContent
	 *            反馈内容
	 * @param userName
	 *            用户名
	 * @param feedbackImage
	 *            图片
	 * @param request
	 * @return
	 */
	@RequestMapping("/feedback/addFeedback")
	public Object addMessage(String feedbackTitle, String feedbackContent, String userName,
			@RequestParam(value = "feedbackImage", required = false) MultipartFile[] feedbackImage) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集

		// 校验参数
		if (StringUtils.isEmpty(feedbackTitle)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("反馈主题不能为空");
			return resultInfo;
		}

		if (StringUtils.isEmpty(feedbackContent)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("反馈内容不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(userName)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("用户姓名不能为空");
			return resultInfo;
		}
		if (feedbackImage != null && feedbackImage.length > 5) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("最多只能上传5张图片");
			return resultInfo;
		}

		// 封装数据
		Feedback feedback = new Feedback();
		List<FeedbackImage> feedbackImages = new ArrayList<FeedbackImage>();
		feedback.setFeedbackTitle(feedbackTitle);
		feedback.setUserName(userName);
		feedback.setCreatedDate(new Date());
		feedback.setFeedbackContent(feedbackContent);
		// 处理图片
		if (feedbackImage != null) {
			for (MultipartFile imageFile : feedbackImage) {
				String feedbackImageFileName = System.currentTimeMillis() + imageFile.getOriginalFilename();
				String imageUrl = null;
				try {
					imageUrl = UploadFileUtils.uploadFile("feedback", feedbackImageFileName, imageFile);
					FeedbackImage image = new FeedbackImage();
					image.setImageUrl(imageUrl);
					image.setCreatedDate(new Date());
					feedbackImages.add(image);
				} catch (Exception e) {
					e.printStackTrace();
					resultInfo.setCode("-1");
					resultInfo.setMessage("上传反馈图片失败");
					return resultInfo;
				}
			}
		} else {
			FeedbackImage image = new FeedbackImage();
			image.setImageUrl("null");
			feedbackImages.add(image);
		}
		// 调用service层的方法
		try {
			Object object = feedbackService.addFeedback(feedback, feedbackImages);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加反馈异常");
			return resultInfo;
		}
	}

	/**
	 * 获取反馈列表
	 * 
	 * @param pageNum
	 *            起始页
	 * @param pageSize
	 *            每页显示数量
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return
	 */
	@RequestMapping(value = "/feedback/getFeedbackList")
	public @ResponseBody Object getNewsList(HttpServletRequest request, HttpServletResponse response) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集

		HashMap<String, Object> params = new HashMap<String, Object>();
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		System.out.println(startDate);
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
		if (StringUtils.isEmpty(startDate)) {
			params.put("startDate", startDate);
		}
		if (StringUtils.isEmpty(endDate)) {
			params.put("endDate", endDate);
		}
		PagingTool pageTool = new PagingTool(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		pageTool.setParams(params);
		int totalCount = 0;

		try {
			totalCount = (int) feedbackService.countTotal(pageTool);
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

		List<Feedback> feedbacks = null;
		try {
			feedbacks = feedbackService.getFeedbackList(pageTool);
		} catch (Exception e) {
			reInfo.setMessage("获取列表异常！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (feedbacks.size() == 0) {
			reInfo.setMessage("列表为空！");
			reInfo.setResult(new ArrayList<Feedback>());
			return reInfo;
		}
		reInfo.setCode("0");
		reInfo.setResult(feedbacks);
		reInfo.setTotal(totalCount);
		reInfo.setMessage("获取列表成功！");
		return reInfo;
	}
}
