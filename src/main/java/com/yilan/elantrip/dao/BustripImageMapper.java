package com.yilan.elantrip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yilan.elantrip.domain.BustripImage;

public interface BustripImageMapper {
    int deleteByPrimaryKey(Integer bustripImageId);

    int insert(BustripImage record);

    int insertSelective(BustripImage record);

    BustripImage selectByPrimaryKey(Integer bustripImageId);

    int updateByPrimaryKeySelective(BustripImage record);

    int updateByPrimaryKey(BustripImage record);
    
    int addBustripImages(@Param("image") List<BustripImage> imagesList);

}