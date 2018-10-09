package com.yilan.elantrip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yilan.elantrip.domain.ProdAttentionTime;

public interface ProdAttentionTimeMapper {
    int deleteByPrimaryKey(Integer attentionTimeId);

    int insert(ProdAttentionTime record);

    int insertSelective(ProdAttentionTime record);

    ProdAttentionTime selectByPrimaryKey(Integer attentionTimeId);

    int updateByPrimaryKeySelective(ProdAttentionTime record);

    int updateByPrimaryKey(ProdAttentionTime record);
    
    int insertBatchTime(@Param("timeList") List<ProdAttentionTime> prodAttentionTimes);
}