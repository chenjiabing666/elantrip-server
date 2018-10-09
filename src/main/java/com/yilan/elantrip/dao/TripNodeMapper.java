package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.BustripNode;
import com.yilan.elantrip.domain.TripNode;
import com.yilan.elantrip.util.PagingTool;

public interface TripNodeMapper {
    int deleteByPrimaryKey(Integer nodeId);

    int insert(TripNode record);

    int insertSelective(TripNode record);

    TripNode selectByPrimaryKey(Integer nodeId);

    int updateByPrimaryKeySelective(TripNode record);

    int updateByPrimaryKey(TripNode record);
    
    List<TripNode> getBustripNodeList(PagingTool pagingTool);
    
    int countNodeTotal(PagingTool pagingTool);
}