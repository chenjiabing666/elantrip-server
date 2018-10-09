package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.HelpImage;

public interface HelpImageMapper {
    int deleteByPrimaryKey(Integer tHelpImageId);

    int insert(HelpImage record);

    int insertSelective(HelpImage record);

    HelpImage selectByPrimaryKey(Integer tHelpImageId);

    int updateByPrimaryKeySelective(HelpImage record);

    int updateByPrimaryKey(HelpImage record);
    
    int addImages(List<HelpImage> helpImages);
}