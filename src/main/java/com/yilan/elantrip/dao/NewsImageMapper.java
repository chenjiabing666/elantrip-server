package com.yilan.elantrip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yilan.elantrip.domain.NewsImage;
import com.yilan.elantrip.util.PagingTool;

public interface NewsImageMapper {
	int deleteByPrimaryKey(Integer imageId);

	int insert(NewsImage record);

	int insertSelective(NewsImage record);

	NewsImage selectByPrimaryKey(Integer aaaId);

	NewsImage selectNewsImage(Integer imageId);

	int updateByPrimaryKeySelective(NewsImage record);

	int updateByPrimaryKey(NewsImage record);
    
	int countTotal(PagingTool pagingTool);

	List<NewsImage> selectNewsImage(NewsImage record);

	List<NewsImage> selectNewsImageList(PagingTool pagingTool);

	int insertBatchImage(@Param("imageList") List<NewsImage> imageList);
	
	int deleteByNewsId(Integer newsId);
}