package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.News;
import com.yilan.elantrip.domain.rs.RSnewsImage;
import com.yilan.elantrip.util.PagingTool;

public interface NewsMapper {

	int deleteByPrimaryKey(Integer newsId);

    int insert(News record);
    
    int insertSelective(News record);

    News selectByPrimaryKey(Integer newsId);

    int updateByPrimaryKeySelective(News record);
   
    int updateByPrimaryKey(News record);

    int countTotal(PagingTool pagingTool);

	List<News> selectNewsList(PagingTool pagingTool);
	
	RSnewsImage selectNewsDetail(Integer newsId);
}