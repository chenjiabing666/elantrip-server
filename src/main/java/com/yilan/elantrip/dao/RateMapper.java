package com.yilan.elantrip.dao;

import com.yilan.elantrip.domain.Rate;

public interface RateMapper {
    int deleteByPrimaryKey(Integer rateId);

    int insert(Rate record);

    int insertSelective(Rate record);

    Rate selectByPrimaryKey(Integer rateId);

    int updateByPrimaryKeySelective(Rate record);

    int updateByPrimaryKey(Rate record);
    
	Rate getRate(int rate);
}