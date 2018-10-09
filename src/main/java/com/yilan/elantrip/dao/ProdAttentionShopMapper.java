package com.yilan.elantrip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yilan.elantrip.domain.ProdAttentionShop;

public interface ProdAttentionShopMapper {
    int deleteByPrimaryKey(Integer attentionShopId);

    int insert(ProdAttentionShop record);

    int insertSelective(ProdAttentionShop record);

    ProdAttentionShop selectByPrimaryKey(Integer attentionShopId);

    int updateByPrimaryKeySelective(ProdAttentionShop record);

    int updateByPrimaryKey(ProdAttentionShop record);
    
    int insertBatchShap(@Param("shopList") List<ProdAttentionShop> prodAttentionShops);
}