package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.Bustrip;
import com.yilan.elantrip.util.PagingTool;

public interface BustripMapper {
    int deleteByPrimaryKey(Short tripId);

    int insert(Bustrip record);

    int insertSelective(Bustrip record);

    Bustrip selectByPrimaryKey(Short tripId);

    int updateByPrimaryKeySelective(Bustrip record);

    int updateByPrimaryKey(Bustrip record);
    
    List<Bustrip> getBustripList(PagingTool pagingTool);
    
    int countTotal(PagingTool pagingTool);
}