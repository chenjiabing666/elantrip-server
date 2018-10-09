package com.yilan.elantrip.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.yilan.elantrip.dao.FeedbackImageMapper;
import com.yilan.elantrip.dao.FeedbackMapper;
import com.yilan.elantrip.domain.Feedback;
import com.yilan.elantrip.domain.FeedbackImage;
import com.yilan.elantrip.domain.rs.RSfeedbackImage;
import com.yilan.elantrip.service.FeedbackService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;



@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Resource
	private FeedbackMapper feedbackMapper;
	@Resource
	private FeedbackImageMapper feedbackImageMapper;
	@Override
	public Object addFeedback(Feedback feedback,List<FeedbackImage> feedbackImages) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		// 插入反馈
		int count = feedbackMapper.insertSelective(feedback);
		if (count == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加失败");
			return resultInfo;
		}
		// 插入图片
		for (FeedbackImage feedbackImage : feedbackImages) {
			feedbackImage.setFeedbackId(feedback.getFeedbackId());
			feedbackImage.setCreatedDate(new Date());
			int count1 = feedbackImageMapper.insertSelective(feedbackImage);
			if (count1 == 0) {
				throw new RuntimeException();
			}
		}
		resultInfo.setCode("0");
		resultInfo.setMessage("添加成功");
		return resultInfo;
	}

	@Override
	public Object getFeedbackById(int feedbackId) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		RSfeedbackImage rSfeedbackImage = null;
		try {
			rSfeedbackImage =  feedbackMapper.selectFeedbackDetail(feedbackId);
			System.out.println(rSfeedbackImage);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取详情异常");
		}	
		resultInfo.setCode("0");
		resultInfo.setMessage("获取成功");
		resultInfo.setResult(rSfeedbackImage);
		return resultInfo;
	}

	@Override
	public Object countTotal(PagingTool pagingTool) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = feedbackMapper.countTotal(pagingTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取总数异常");
		}
		return count;
	}

	@Override
	public List<Feedback> getFeedbackList(PagingTool pagingTool) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		List<Feedback> feedbackList = null;
		try {
			feedbackList = feedbackMapper.selectFeedbackList(pagingTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取反馈列表异常");
		}

		return feedbackList;
	}

	
}
