package com.yilan.elantrip.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.yilan.elantrip.dao.CouponMapper;
import com.yilan.elantrip.domain.Coupon;
import com.yilan.elantrip.domain.rs.RScoupon;
import com.yilan.elantrip.service.CouponService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;



@Service
public class CouponServiceImpl implements CouponService{
	
	@Resource
	private CouponMapper couponMapper;
	@Override
	public Object addCoupon(Coupon coupon) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		// 插入消息
		int count = couponMapper.insertSelective(coupon);
		if (count == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加失败");
			return resultInfo;
		}
		resultInfo.setCode("0");
		resultInfo.setMessage("添加成功");
		return resultInfo;
	}

	@Override
	public Object getCouponById(int couponId) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		RScoupon rScoupon = null;
		try {
			rScoupon =  couponMapper.selectCouponDetail(couponId);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取详情异常");
		}
		resultInfo.setCode("0");
		resultInfo.setMessage("获取成功");
		resultInfo.setResult(rScoupon);
		return resultInfo;
	}

	@Override
	public Object countTotal(PagingTool pagingTool) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = couponMapper.countTotal(pagingTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取总数异常");
		}
		return count;
	}

	@Override
	public List<Coupon> getCouponList(PagingTool pagingTool) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		List<Coupon> couponList = null;
		try {
			couponList = couponMapper.selectCouponList(pagingTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取新闻列表异常");
		}

		return couponList;
	}

	
}
