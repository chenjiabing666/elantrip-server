package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.ProdLanguage;

public interface ProdLanguageMapper {
    int deleteByPrimaryKey(Integer languageServiceId);

    int insert(ProdLanguage record);

    int insertSelective(ProdLanguage record);

    ProdLanguage selectByPrimaryKey(Integer languageServiceId);

    int updateByPrimaryKeySelective(ProdLanguage record);

    int updateByPrimaryKey(ProdLanguage record);

	List<ProdLanguage> selectByProdId(int productId);
}