package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.ProdBright;

public interface ProdBrightMapper {
    int deleteByPrimaryKey(Integer brightId);

    int insert(ProdBright record);

    int insertSelective(ProdBright record);

    ProdBright selectByPrimaryKey(Integer brightId);

    int updateByPrimaryKeySelective(ProdBright record);

    int updateByPrimaryKey(ProdBright record);

	List<ProdBright> selectByProdId(int productId);
}