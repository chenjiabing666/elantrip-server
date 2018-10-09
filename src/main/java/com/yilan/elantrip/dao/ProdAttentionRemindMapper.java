package com.yilan.elantrip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yilan.elantrip.domain.ProdAttentionRemind;

public interface ProdAttentionRemindMapper {
    int deleteByPrimaryKey(Integer attentionRemindId);

    int insert(ProdAttentionRemind record);

    int insertSelective(ProdAttentionRemind record);

    ProdAttentionRemind selectByPrimaryKey(Integer attentionRemindId);

    int updateByPrimaryKeySelective(ProdAttentionRemind record);

    int updateByPrimaryKey(ProdAttentionRemind record);
    
    int insertBatchRemind(@Param("remindList") List<ProdAttentionRemind> prodAttentionReminds);
}