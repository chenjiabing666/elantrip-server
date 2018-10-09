package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.ProdIncOther;

public interface ProdIncOtherMapper {
    int deleteByPrimaryKey(Integer incOtherId);

    int insert(ProdIncOther record);

    int insertSelective(ProdIncOther record);

    ProdIncOther selectByPrimaryKey(Integer incOtherId);

    int updateByPrimaryKeySelective(ProdIncOther record);

    int updateByPrimaryKey(ProdIncOther record);

    List<ProdIncOther> selectByProdId(int productId);
} 