package com.yilan.elantrip.dao;

import com.yilan.elantrip.domain.ProdPinfo;

public interface ProdPinfoMapper {
    int deleteByPrimaryKey(Integer pinfoId);

    int insert(ProdPinfo record);

    int insertSelective(ProdPinfo record);

    ProdPinfo selectByPrimaryKey(Integer pinfoId);

    int updateByPrimaryKeySelective(ProdPinfo record);

    int updateByPrimaryKey(ProdPinfo record);
}