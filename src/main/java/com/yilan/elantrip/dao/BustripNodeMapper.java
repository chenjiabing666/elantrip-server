package com.yilan.elantrip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yilan.elantrip.domain.BustripNode;
import com.yilan.elantrip.util.PagingTool;

public interface BustripNodeMapper {
    int deleteByPrimaryKey(Integer bustripNodeId);

    int insert(BustripNode record);

    int addBustripNodes(@Param(value="bustripNodes")List<BustripNode> bustripNodes);

    BustripNode selectByPrimaryKey(Integer bustripNodeId);

    int updateByPrimaryKeySelective( BustripNode record);

    int updateByPrimaryKey(BustripNode record);
    

    
}