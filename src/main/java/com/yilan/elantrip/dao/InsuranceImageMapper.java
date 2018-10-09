package com.yilan.elantrip.dao;

import com.yilan.elantrip.domain.InsuranceImage;

public interface InsuranceImageMapper {
    int deleteByPrimaryKey(Integer insuranceImageId);

    int insert(InsuranceImage record);

    int insertSelective(InsuranceImage record);

    InsuranceImage selectByPrimaryKey(Integer insuranceImageId);

    int updateByPrimaryKeySelective(InsuranceImage record);

    int updateByPrimaryKey(InsuranceImage record);
}