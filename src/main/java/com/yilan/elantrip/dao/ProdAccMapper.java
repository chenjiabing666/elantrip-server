package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.ProdAcc;

public interface ProdAccMapper {
    int deleteByPrimaryKey(Integer incAccId);

    int insert(ProdAcc record);

    int insertSelective(ProdAcc record);

    ProdAcc selectByPrimaryKey(Integer incAccId);

    int updateByPrimaryKeySelective(ProdAcc record);

    int updateByPrimaryKey(ProdAcc record);

	List<ProdAcc> selectByProdId(int productId);
}