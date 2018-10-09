package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.ProdTraffic;

public interface ProdTrafficMapper {
    int deleteByPrimaryKey(Integer incTrafficId);

    int insert(ProdTraffic record);

    int insertSelective(ProdTraffic record);

    ProdTraffic selectByPrimaryKey(Integer incTrafficId);

    int updateByPrimaryKeySelective(ProdTraffic record);

    int updateByPrimaryKey(ProdTraffic record);

	List<ProdTraffic> selectByProdId(int productId);
}