package com.yilan.elantrip.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yilan.elantrip.domain.Feedback;
import com.yilan.elantrip.domain.FeedbackImage;
import com.yilan.elantrip.util.PagingTool;

/**
 * 反馈的Service
 * 
 * @author Administrator
 *
 */
@Transactional // 开启事务注解,默认运行时异常事务回滚
public interface FeedbackService {
	/**
	 * 添加反馈
	 * 
	 * @param feedback
	 * @return
	 * @throws Exception
	 */
	Object addFeedback(Feedback feedback,List<FeedbackImage> newsImages)throws Exception;
	/**
	 * 获取反馈详细信息
	 * 
	 * @param feedbackId  反馈Id
	 * @return
	 * @throws Exception
	 */
	Object getFeedbackById(int feedbackId)throws Exception;

	/**
	 * 获取反馈总数
	 * 
	 * @return
	 * @throws Exception
	 */
	Object countTotal(PagingTool pagingTool)throws Exception;
	/**
	 * 获取反馈列表
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Feedback> getFeedbackList(PagingTool pagingTool)throws Exception;


}
