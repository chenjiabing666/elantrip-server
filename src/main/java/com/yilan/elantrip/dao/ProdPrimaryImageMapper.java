package com.yilan.elantrip.dao;

import com.yilan.elantrip.domain.ProdPrimaryImage;

public interface ProdPrimaryImageMapper {
    int deleteByPrimaryKey(Integer imageId);

    int insert(ProdPrimaryImage record);

    int insertSelective(ProdPrimaryImage record);

    ProdPrimaryImage selectByPrimaryKey(Integer imageId);

    int updateByPrimaryKeySelective(ProdPrimaryImage record);

    int updateByPrimaryKey(ProdPrimaryImage record);

	ProdPrimaryImage selectByProdId(int productId);
}