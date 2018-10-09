package com.yilan.elantrip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yilan.elantrip.domain.BustripStock;

public interface BustripStockMapper {
    int deleteByPrimaryKey(Integer stockId);

    int insert(BustripStock record);

    int insertSelective(BustripStock record);

    BustripStock selectByPrimaryKey(Integer stockId);

    int updateByPrimaryKeySelective(BustripStock record);

    int updateByPrimaryKey(BustripStock record);
    
    int modifyBustripStock(@Param(value="bustripStock")  List<BustripStock> bustripStocks);
}