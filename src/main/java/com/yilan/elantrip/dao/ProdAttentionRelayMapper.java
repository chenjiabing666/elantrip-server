package com.yilan.elantrip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yilan.elantrip.domain.ProdAttentionRelay;

public interface ProdAttentionRelayMapper {
    int deleteByPrimaryKey(Integer attentionRelayId);

    int insert(ProdAttentionRelay record);

    int insertSelective(ProdAttentionRelay record);

    ProdAttentionRelay selectByPrimaryKey(Integer attentionRelayId);

    int updateByPrimaryKeySelective(ProdAttentionRelay record);

    int updateByPrimaryKey(ProdAttentionRelay record);
    
	int insertBatchRelay(@Param("relayList") List<ProdAttentionRelay> prodAttentionRelays);
}