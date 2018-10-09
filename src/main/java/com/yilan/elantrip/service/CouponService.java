package com.yilan.elantrip.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yilan.elantrip.domain.Coupon;
import com.yilan.elantrip.util.PagingTool;

/**
 * 优惠券的Service
 * 
 * @author Administrator
 *
 */
@Transactional // 开启事务注解,默认运行时异常事务回滚
public interface CouponService {

	/**
	 * 添加优惠券
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Object addCoupon(Coupon coupon)throws Exception;
	/**
	 * 获取优惠券详细信息
	 * 
	 * @param userId
	 *            用户Id
	 * @return
	 * @throws Exception
	 */
	Object getCouponById(int couponId)throws Exception;

	/**
	 * 获取优惠券总数
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Object countTotal(PagingTool pagingTool)throws Exception;
	/**
	 * 获取优惠券列表
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<Coupon> getCouponList(PagingTool pagingTool)throws Exception;
}
