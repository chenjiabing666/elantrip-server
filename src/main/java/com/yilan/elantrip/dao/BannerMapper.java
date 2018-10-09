package com.yilan.elantrip.dao;


import java.util.List;

import com.yilan.elantrip.domain.Banner;
import com.yilan.elantrip.util.PagingTool;


public interface BannerMapper {
	
	
    int deleteByPrimaryKey(Integer bannerId);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Integer bannerId);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);
    
    List<Banner> selectBannerList(PagingTool pagingTool);
    
    int countTotal(PagingTool pagingTool);    
    
}