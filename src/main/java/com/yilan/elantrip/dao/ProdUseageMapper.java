package com.yilan.elantrip.dao;

import com.yilan.elantrip.domain.ProdUseage;

public interface ProdUseageMapper {
    int deleteByPrimaryKey(Integer useageId);

    int insert(ProdUseage record);

    int insertSelective(ProdUseage record);

    ProdUseage selectByPrimaryKey(Integer useageId);

    int updateByPrimaryKeySelective(ProdUseage record);

    int updateByPrimaryKey(ProdUseage record);
}