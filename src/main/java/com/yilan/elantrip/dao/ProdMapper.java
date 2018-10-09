package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.Prod;
import com.yilan.elantrip.util.PagingTool;

public interface ProdMapper {
    int deleteByPrimaryKey(Integer prodId);

    int insert(Prod record);

    int insertSelective(Prod record);

    Prod selectByPrimaryKey(Integer prodId);

    int updateByPrimaryKeySelective(Prod record);

    int updateByPrimaryKey(Prod record);

	int countTotal(PagingTool pagingTool);

	List<Prod> selectProductList(PagingTool pagingTool);
}