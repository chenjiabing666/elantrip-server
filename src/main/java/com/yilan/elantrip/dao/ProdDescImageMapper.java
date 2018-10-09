package com.yilan.elantrip.dao;



import java.util.List;

import com.yilan.elantrip.domain.ProdDescImage;

public interface ProdDescImageMapper {
    int deleteByPrimaryKey(Integer descImageId);

    int insert(ProdDescImage record);

    int insertSelective(ProdDescImage record);

    ProdDescImage selectByPrimaryKey(Integer descImageId);

    int updateByPrimaryKeySelective(ProdDescImage record);

    int updateByPrimaryKey(ProdDescImage record);

	List<ProdDescImage> selectByProdId(int productId);
    
//    int insertBatchImage (@Param("descImageList") List<ProdDescImage> descImages);

}