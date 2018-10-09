package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.ProdRestaurant;

public interface ProdRestaurantMapper {
    int deleteByPrimaryKey(Integer incRestaurantId);

    int insert(ProdRestaurant record);

    int insertSelective(ProdRestaurant record);

    ProdRestaurant selectByPrimaryKey(Integer incRestaurantId);

    int updateByPrimaryKeySelective(ProdRestaurant record);

    int updateByPrimaryKey(ProdRestaurant record);

	List<ProdRestaurant> selectByProdId(int productId);
}