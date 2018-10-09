package com.yilan.elantrip.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.yilan.elantrip.domain.Banner;
import com.yilan.elantrip.domain.Comment;
import com.yilan.elantrip.service.CommentService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;

@RestController
public class CommentController {
	
	
	//注入
	@Resource
	private CommentService commentService;
	
	public CommentService getComment() {
		return commentService;
	}

	public void setComment(CommentService commentService) {
		this.commentService = commentService;
	}

	/**
	 * 添加点评
	 * @param orderId
	 * @param prodId
	 * @param tripType
	 * @param product
	 * @param prodGrade
	 * @param trip
	 * @param tripGrade
	 * @param traffic
	 * @param trafficGrade
	 * @param hotel
	 * @param hotelGrade
	 * @param guide
	 * @param guideGrade
	 * @param food
	 * @param foodGrade
	 * @param grade
	 * @param adviceSeason
	 * @param advicePeople
	 * @param adviceTime
	 * @param advicePref
	 * @param allReview
	 * @return
	 */
	@RequestMapping("/Comment/addComment")
	Object addComment(
			@RequestParam 
			String orderId, //订单ID
			String prodId, //产品ID
			String tripType, //旅游方式
			String product, //产品
			String prodGrade, //产品评分
			String trip, //行程
			String tripGrade, //行程评分
			String traffic, //交通
			String trafficGrade, //交通评分
			String hotel, //酒店
			String hotelGrade, //酒店评分
			String guide, //导游
			String guideGrade, //导游评分
			String food, //餐食
			String foodGrade, //餐食评分
			String grade, //总体评分
			String allReview, //总体评价
			String adviceSeason, //推荐季节
			String advicePeople, //推荐人群
			String adviceTime, //推荐游玩时间
			String advicePref //特别推荐
			 ){
         ResultInfo reInfo = new ResultInfo();
         
//         if (StringUtils.isEmpty(orderId)) {
//        	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
//        	 reInfo.setMessage("");
// 			return reInfo;
// 		}
//         if (StringUtils.isEmpty(prodId)) {
//        	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
//        	 reInfo.setMessage("");
// 			return reInfo;
// 		}        
         if (StringUtils.isEmpty(tripType)) {
       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
       	 reInfo.setMessage("请选择旅行方式");
			return reInfo;
		}         
          
//         if (StringUtils.isEmpty(product)) {
//       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
//       	 reInfo.setMessage("请填写评价");
//			return reInfo;
//		}
         if (StringUtils.isEmpty(prodGrade)) {
       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
       	 reInfo.setMessage("请对产品评分");
			return reInfo;
		}
//         if (StringUtils.isEmpty(trip)) {
//       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
//       	 reInfo.setMessage("请填写评价");
//			return reInfo;
//		}
         if (StringUtils.isEmpty(tripGrade)) {
       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
       	 reInfo.setMessage("请对旅行线路评分");
			return reInfo;
		}
//         if (StringUtils.isEmpty(traffic)) {
//       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
//       	 reInfo.setMessage("");
//			return reInfo;
//		}
         if (StringUtils.isEmpty(trafficGrade)) {
       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
       	 reInfo.setMessage("请对交通评分");
			return reInfo;
		}
//         if (StringUtils.isEmpty(hotel)) {
//       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
//       	 reInfo.setMessage("请填写邮箱");
//			return reInfo;
//		}
         if (StringUtils.isEmpty(hotelGrade)) {
       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
       	 reInfo.setMessage("请对酒店评分");
			return reInfo;
		}
//         if (StringUtils.isEmpty(guide)) {
//       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
//       	 reInfo.setMessage("请填写邮箱");
//			return reInfo;
//		}
         if (StringUtils.isEmpty(guideGrade)) {
       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
       	 reInfo.setMessage("评一下导游把！");
			return reInfo;
		}
//         if (StringUtils.isEmpty(food)) {
//       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
//       	 reInfo.setMessage("请填写邮箱");
//			return reInfo;
//		}
         if (StringUtils.isEmpty(foodGrade)) {
       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
       	 reInfo.setMessage("评评食物有多好吃");
			return reInfo;
		}
         if (StringUtils.isEmpty(grade)) {
       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
       	 reInfo.setMessage("总体评价");
			return reInfo;
		}
         if (StringUtils.isEmpty(adviceSeason)) {
       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
       	 reInfo.setMessage("推荐季节");
			return reInfo;
		}
         if (StringUtils.isEmpty(advicePeople)) {
       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
       	 reInfo.setMessage("推荐人群");
			return reInfo;
		}
         if (StringUtils.isEmpty(adviceTime)) {
       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
       	 reInfo.setMessage("推荐时间");
			return reInfo;
		}
         if (StringUtils.isEmpty(advicePref)) {
       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
       	 reInfo.setMessage("特别推荐");
			return reInfo;
		}
         
		Comment comment = new Comment();

         //数据封装
        comment.setOrderId(Integer.valueOf(orderId));
		comment.setProdId(Integer.valueOf(prodId));
		comment.setTripType(Integer.valueOf(tripType));
		comment.setProductName(product);
		comment.setProdGrade(Integer.valueOf(prodGrade));
	    comment.setTripContent(trip);
	    comment.setTripGrade(Integer.valueOf(tripGrade));
	    comment.setTrafficContent(traffic);
	    comment.setTrafficGrade(Integer.valueOf(traffic));
	    comment.setHotelContent(hotel);
	    comment.setHotelGrade(Integer.valueOf(hotelGrade));
	    comment.setGuideContent(guide);
	    comment.setGuideGrade(Integer.valueOf(guideGrade));
	    comment.setFoodContent(food);
	    comment.setFoodGrade(Integer.valueOf(foodGrade));
	    comment.setGrade(Integer.valueOf(grade));
	    comment.setAdvicePeople(Integer.valueOf(advicePeople));
	    comment.setAdviceSeason(Integer.valueOf(adviceSeason));
	    comment.setAdvicePreferred(Integer.valueOf(advicePref));
	    comment.setAdviceTime(Integer.valueOf(adviceTime));
	    comment.setCreatedDatetime(new Date()); 
	    comment.setAllReview(allReview);
	     try {
	    	 //调用Service
			Object object = commentService.addComment(comment);
			return object;
		} catch (Exception e) {
			reInfo.setCode("-1");
			reInfo.setMessage("添加异常");
			return reInfo;		
			}
         
	}
	/**
	 * 删除评论
	 * @param commentId
	 * @return
	 */
	@RequestMapping("/comment/deleteComment")
	Object deleteComment(@RequestParam String commentId) {
		ResultInfo resultInfo = new ResultInfo();
		// 校验参数
				if (StringUtils.isEmpty(commentId)) {
					resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
					resultInfo.setMessage("评价编号不能为空");
					return resultInfo;
				}

				// 调用service层的方法
				try {
					Object object = commentService.deleteComment(Integer.valueOf(commentId));
					return object;
				} catch (Exception e) {
					resultInfo.setCode("-1");
					resultInfo.setMessage("删除异常");
					return resultInfo;
				}
			}
	
	/**
	 * Description: 查看广告详情
	 * 
	 * @param request
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/comment/getCommentById")
	public @ResponseBody Object getCommentById(HttpServletRequest request,
			HttpSession session, HttpServletResponse response) {
		ResultInfo rsInfo = new ResultInfo();

		String commentId = request.getParameter("commentId");
		
		if (commentId == null || commentId.equals("")) {
			rsInfo.setMessage("评论ID不能为空！");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		Object comment = null;
		try {
			comment = commentService.getCommentByCommentId(Integer.valueOf(commentId));
		} catch (Exception e) {
			e.printStackTrace();
			rsInfo.setMessage("获取评论信息失败！");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		if (comment == null) {
			rsInfo.setMessage("评论信息不存在！");
			rsInfo.setResult(new Banner());
			rsInfo.setTotal(0);
			return rsInfo;
		}
		rsInfo.setResult(comment);
		rsInfo.setMessage("获取评论成功！");
		return rsInfo;
	}

	/**
	 * @description 获取评论列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/comment/getCommentList")
	public @ResponseBody Object getCommentList(HttpServletRequest request,
			HttpServletResponse response) {
		ResultInfo rsInfo = new ResultInfo();

		HashMap<String, Object> params = new HashMap<String, Object>();

		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
//		String commentId = request.getParameter("comment_id");

		if (pageNum == null || pageNum.equals("")) {
			rsInfo.setMessage("页码不能为空！");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		if (pageSize == null || pageSize.equals("")) {
			rsInfo.setMessage("页大小不能为空！");
			rsInfo.setCode("-1");
			return rsInfo;
		}

//		if (commentId != null && !commentId.equals("")) {
//			params.put("commentId", commentId);
//		}

		PagingTool pageTool = new PagingTool(Integer.parseInt(pageNum),
				Integer.parseInt(pageSize));
		pageTool.setParams(params);
		int totalCount = 0;
		try {
			totalCount = commentService.countTotal(pageTool);
		} catch (Exception e) {
			rsInfo.setMessage("获取评论总数异常！");
			rsInfo.setCode("-1");
			return rsInfo;
		}

		if (totalCount == 0) {
			rsInfo.setMessage("评论数量为零");
			rsInfo.setResult(null);
			return rsInfo;
		}

		List<Comment> commentList = null;
		try {
			commentList = commentService.getCommentList(pageTool);
		} catch (Exception e) {
			rsInfo.setMessage("	");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		if (commentList.size() == 0) {
			rsInfo.setMessage("评论列表为空！");
			rsInfo.setResult(new ArrayList<Banner>());
			return rsInfo;
		}
		rsInfo.setResult(commentList);
		rsInfo.setTotal(totalCount);
		rsInfo.setMessage("获取评论列表成功！");
		return rsInfo;
	}
	/**
	 * 回复评论
	 * @param commentId
	 * @param reply
	 * @return
	 */
	@RequestMapping("/comment/reply")
	Object replyComment(@RequestParam String commentId, String reply) {
		
		
		ResultInfo resultInfo = new ResultInfo();
		if (StringUtils.isEmpty(reply)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("回复不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(commentId)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("测试，ID不能为空");
			return resultInfo;
		}
		Comment comment = new Comment();
		comment.setReply(reply);
		comment.setCommentId(Integer.valueOf(commentId));
		try {
			Object object = commentService.commentReply(comment);
			return object;
		}catch (Exception e) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("回复失败");
			return resultInfo;
		}
		
	}
	}
    

