package com.yilan.elantrip.dao;

import com.yilan.elantrip.domain.CouponDetails;

public interface CouponDetailsMapper {
    int deleteByPrimaryKey(Integer userCouponId);

    int insert(CouponDetails record);

    int insertSelective(CouponDetails record);

    CouponDetails selectByPrimaryKey(Integer userCouponId);

    int updateByPrimaryKeySelective(CouponDetails record);

    int updateByPrimaryKey(CouponDetails record);
}