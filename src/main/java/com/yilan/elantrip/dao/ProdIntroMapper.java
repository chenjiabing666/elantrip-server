package com.yilan.elantrip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yilan.elantrip.domain.ProdIntro;

public interface ProdIntroMapper {
    int deleteByPrimaryKey(Integer introId);

    int insert(ProdIntro record);

    int insertSelective(ProdIntro record);

    ProdIntro selectByPrimaryKey(Integer introId);

    int updateByPrimaryKeySelective(ProdIntro record);

    int updateByPrimaryKey(ProdIntro record);
    
    int addProdIntro(@Param(value="prodIntro") List<ProdIntro> prodIntros);
    
    List<ProdIntro> selectByProdId(int prodId);
}