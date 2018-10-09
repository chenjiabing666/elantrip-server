package com.yilan.elantrip.dao;

import com.yilan.elantrip.domain.ProdCar;

public interface ProdCarMapper {
    int deleteByPrimaryKey(Integer prodCarId);

    int insert(ProdCar record);

    int insertSelective(ProdCar record);

    ProdCar selectByPrimaryKey(Integer prodCarId);

    int updateByPrimaryKeySelective(ProdCar record);

    int updateByPrimaryKey(ProdCar record);
}