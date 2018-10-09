package com.yilan.elantrip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yilan.elantrip.domain.ProdAttentionOther;

public interface ProdAttentionOtherMapper {
    int deleteByPrimaryKey(Integer attentionOtherId);

    int insert(ProdAttentionOther record);

    int insertSelective(ProdAttentionOther record);

    ProdAttentionOther selectByPrimaryKey(Integer attentionOtherId);

    int updateByPrimaryKeySelective(ProdAttentionOther record);

    int updateByPrimaryKey(ProdAttentionOther record);
    
    int insertBatchOther(@Param("otherList") List<ProdAttentionOther> prodAttentionOthers);
}