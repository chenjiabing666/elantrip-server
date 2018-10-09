package com.yilan.elantrip.dao;

import com.yilan.elantrip.domain.ProdVideo;

public interface ProdVideoMapper {
    int deleteByPrimaryKey(Integer prodVideoId);

    int insert(ProdVideo record);

    int insertSelective(ProdVideo record);

    ProdVideo selectByPrimaryKey(Integer prodVideoId);

    int updateByPrimaryKeySelective(ProdVideo record);

    int updateByPrimaryKey(ProdVideo record);
}