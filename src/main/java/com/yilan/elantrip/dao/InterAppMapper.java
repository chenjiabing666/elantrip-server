package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.InterApp;
import com.yilan.elantrip.util.PagingTool;

public interface InterAppMapper {
    int deleteByPrimaryKey(Integer applyId);

    int insert(InterApp record);

    int insertSelective(InterApp record);

    InterApp selectByPrimaryKey(Integer applyId);

    int updateByPrimaryKeySelective(InterApp record);

    int updateByPrimaryKey(InterApp record);
    
    int countTotal(PagingTool pagingTool);
    
    List<InterApp> getInterList(PagingTool pagingTool);
}