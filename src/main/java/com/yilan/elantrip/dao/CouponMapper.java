package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.Coupon;
import com.yilan.elantrip.domain.rs.RScoupon;
import com.yilan.elantrip.util.PagingTool;

public interface CouponMapper {
    int deleteByPrimaryKey(Integer couponId);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    Coupon selectByPrimaryKey(Integer couponId);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);
    
	// 查询总数
	int countTotal(PagingTool pagingTool);

	// 查询列表
	List<Coupon> selectCouponList(PagingTool pagingTool);

	// 获取详情
	RScoupon selectCouponDetail(int couponId);
}