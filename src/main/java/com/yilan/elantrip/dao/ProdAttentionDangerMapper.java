package com.yilan.elantrip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yilan.elantrip.domain.ProdAttentionDanger;

public interface ProdAttentionDangerMapper {
    int deleteByPrimaryKey(Integer attentionDangerId);

    int insert(ProdAttentionDanger record);

    int insertSelective(ProdAttentionDanger record);

    ProdAttentionDanger selectByPrimaryKey(Integer attentionDangerId);

    int updateByPrimaryKeySelective(ProdAttentionDanger record);

    int updateByPrimaryKey(ProdAttentionDanger record);
    
    int insertBatchDanger(@Param("dangerList") List<ProdAttentionDanger> prodAttentionDangers);
}