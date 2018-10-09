package com.yilan.elantrip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yilan.elantrip.domain.ProdStock;

public interface ProdStockMapper {
    int deleteByPrimaryKey(Integer stockId);

    int insert(ProdStock record);

    int insertSelective(ProdStock record);

    ProdStock selectByPrimaryKey(Integer stockId);

    int updateByPrimaryKeySelective(ProdStock record);

    int updateByPrimaryKey(ProdStock record);
    
    int insertBatchStock(@Param("stockList") List<ProdStock> prodStocks);
    
	List<ProdStock> selectByProdId(int productId);
}