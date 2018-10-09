package com.yilan.elantrip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yilan.elantrip.domain.ProdIntroImage;

public interface ProdIntroImageMapper {
    int deleteByPrimaryKey(Integer imageId);

    int insert(ProdIntroImage record);

    int insertSelective(ProdIntroImage record);

    ProdIntroImage selectByPrimaryKey(Integer imageId);

    int updateByPrimaryKeySelective(ProdIntroImage record);

    int updateByPrimaryKey(ProdIntroImage record);
    
    int addProdIntroImage(@Param(value="prodIntroImages") List<ProdIntroImage> prodIntroImages);
    
	List<ProdIntroImage> selectByProdId(int productId);
    
}