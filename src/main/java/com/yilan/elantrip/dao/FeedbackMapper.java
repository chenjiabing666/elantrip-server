package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.Feedback;
import com.yilan.elantrip.domain.rs.RSfeedbackImage;
import com.yilan.elantrip.util.PagingTool;

public interface FeedbackMapper {
	int deleteByPrimaryKey(Integer feedbackId);

	int insert(Feedback record);

	int insertSelective(Feedback record);

	Feedback selectByPrimaryKey(Integer feedbackId);

	int updateByPrimaryKeySelective(Feedback record);

	int updateByPrimaryKey(Feedback record);

	// 查询总数
	int countTotal(PagingTool pagingTool);

	// 查询列表
	List<Feedback> selectFeedbackList(PagingTool pagingTool);

	// 获取详情
	RSfeedbackImage selectFeedbackDetail(Integer feedbackId);
}