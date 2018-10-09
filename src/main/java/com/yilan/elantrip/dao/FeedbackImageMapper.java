package com.yilan.elantrip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yilan.elantrip.domain.FeedbackImage;

public interface FeedbackImageMapper {
	int deleteByPrimaryKey(Integer feedbackImageId);

    int insert(FeedbackImage record);

    int insertSelective(FeedbackImage record);

    FeedbackImage selectByPrimaryKey(Integer feedbackImageId);

    int updateByPrimaryKeySelective(FeedbackImage record);

    int updateByPrimaryKey(FeedbackImage record);
    
    int insertBatchImage(@Param("imageList") List<FeedbackImage> imageList);
}