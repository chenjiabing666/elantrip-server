package com.yilan.elantrip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yilan.elantrip.domain.CustomImages;


public interface CustomImagesMapper {
    int deleteByPrimaryKey(Integer imageId);

    int insert(CustomImages record);

    int insertSelective(CustomImages record);

    CustomImages selectByPrimaryKey(Integer imageId);

    int updateByPrimaryKeySelective(CustomImages record);

    int updateByPrimaryKey(CustomImages record);
    
    int addImages(@Param("image") List<CustomImages> imagesList);
}