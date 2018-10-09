package com.yilan.elantrip.dao;

import com.yilan.elantrip.domain.BustripNodeImage;

public interface BustripNodeImageMapper {
    int deleteByPrimaryKey(Integer btnodeImageId);

    int insert(BustripNodeImage record);

    int insertSelective(BustripNodeImage record);

    BustripNodeImage selectByPrimaryKey(Integer btnodeImageId);

    int updateByPrimaryKeySelective(BustripNodeImage record);

    int updateByPrimaryKey(BustripNodeImage record);
}