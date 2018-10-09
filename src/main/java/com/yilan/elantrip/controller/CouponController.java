package com.yilan.elantrip.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.yilan.elantrip.domain.Coupon;
import com.yilan.elantrip.service.CouponService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;

/**
 * 优惠券的Controller
 * 
 * @author Administrator
 *
 */
@RestController // 直接使用@RestController，不用@ResponseBody
public class CouponController {

	@Resource
	private CouponService couponService; // 注入

	/**
	 * 获取优惠券详情
	 * 
	 * @param couponId
	 *            优惠券Id
	 * @param request
	 * @return
	 */
	@RequestMapping("/coupon/getCoupon")
	public Object getCoupon(HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo();
		String couponId = request.getParameter("couponId");

		// 校验参数
		if (StringUtils.isEmpty(couponId)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("优惠券Id不能为空");
			return resultInfo;
		}

		// 调用service层的方法
		try {
			Object object = couponService.getCouponById(Integer.parseInt(couponId));
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("获取异常");
			return resultInfo;
		}
	}

	/**
	 * 添加优惠券
	 * 
	 * @param couponName
	 *            优惠券名称
	 * @param couponType
	 *            类型
	 * @param price
	 *            面值
	 * @param totalCount
	 *            发放总量
	 * @param requirment
	 *            使用条件
	 * @param fullMoney
	 *            满多少钱
	 * @param usersSuitable
	 *            适用用户
	 * @param recieveType
	 *            领取方式
	 * @param typeId
	 *            品类
	 * @param numLimit
	 *            单账户领券数量限制
	 * @param isEffective
	 *            是否永久有效
	 * @param expiryStartDate
	 *            有效起始时间
	 * @param expiryEndDate
	 *            有效截止时间
	 * @param launchStime
	 *            发放起始时间
	 * @param launchEtime
	 *            发放结束时间
	 * @param request
	 * @return
	 */
	@RequestMapping("/coupon/addCoupon")
	public Object addCoupon(String couponName, String couponType, String price, String totalCount, String requirment,
			String fullMoney, String usersSuitable, String recieveType, String typeId, String numLimit,
			String isEffective, String expiryStartDate, String expiryEndDate, String launchStime, String launchEtime) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集
		Coupon coupon = new Coupon();
		couponName = "1";
		couponType = "2";
		price = "3";
		totalCount = "4";
		requirment = "5";
		usersSuitable = "6";
		recieveType = "7";
		typeId = "8";
		numLimit = "9";
		expiryStartDate = "2018-10-11";
		expiryEndDate = "2019-10-11";
		launchStime = "2014-2-4";
		launchEtime = "2042-12-2";
		// 校验参数
		if (StringUtils.isEmpty(couponName)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("优惠券名称不能为空");
			return resultInfo;
		}

		if (StringUtils.isEmpty(couponType)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("类型不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(price)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("面值不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(totalCount)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("发放总量不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(requirment)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("使用条件不能为空");
			return resultInfo;
		}
		if (!StringUtils.isEmpty(fullMoney)) {
			coupon.setFullMoney(Double.valueOf(fullMoney));
			return resultInfo;
		}
		if (StringUtils.isEmpty(usersSuitable)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("适用用户不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(recieveType)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("领取方式不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(typeId)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("品类不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(numLimit)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("单账户领券数量限制不能为空");
			return resultInfo;
		}
		if (!StringUtils.isEmpty(isEffective)) {
			coupon.setIsEffective(Integer.parseInt(isEffective));
		}
		if (!StringUtils.isEmpty(expiryStartDate)) {
			try {
				coupon.setExpiryStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(expiryStartDate));
			} catch (ParseException e) {
				resultInfo.setMessage("设置有效起始时间异常");
				resultInfo.setCode("-1");
			}
		}
		if (!StringUtils.isEmpty(expiryEndDate)) {
			try {
				coupon.setExpriyEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(expiryEndDate));
			} catch (ParseException e) {
				resultInfo.setMessage("设置有效截止时间异常");
				resultInfo.setCode("-1");
			}
		}
		if (StringUtils.isEmpty(launchStime)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("发放起始时间不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(launchEtime)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("发放结束时间不能为空");
			return resultInfo;
		}
		// 封装数据
		coupon.setCouponName(couponName);
		coupon.setCouponType(Integer.parseInt(couponType));
		coupon.setPrice(Double.valueOf(price));
		coupon.setTotalCount(Integer.parseInt(totalCount));
		coupon.setRequirment(Integer.parseInt(requirment));
		coupon.setUsersSuitable(usersSuitable);
		coupon.setRecieveType(recieveType);
		coupon.setTypeId(Integer.parseInt(typeId));
		coupon.setNumLimit(Integer.parseInt(numLimit));
		try {
			coupon.setLaunchStime(new SimpleDateFormat("yyyy-MM-dd").parse(launchStime));
		} catch (ParseException e1) {
			resultInfo.setMessage("设置发放起始时间异常");
			resultInfo.setCode("-1");
		}
		try {
			coupon.setLaunchEtime(new SimpleDateFormat("yyyy-MM-dd").parse(launchEtime));
		} catch (ParseException e1) {
			resultInfo.setMessage("设置发放截止时间异常");
			resultInfo.setCode("-1");
		}
		coupon.setCreatedDate(new Date());

		// 调用service层的方法

		try {
			Object object = couponService.addCoupon(coupon);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加优惠券异常");
			return resultInfo;
		}
	}

	/**
	 * 获取优惠券列表
	 * 
	 * @param couponType
	 *            优惠券类型
	 * @param couponName
	 *            优惠券名称
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/coupon/getCouponList")
	public @ResponseBody Object getCouponList(String couponType, String couponName, HttpServletRequest request) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集
		HashMap<String, Object> params = new HashMap<String, Object>();
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		if (pageSize == null || pageSize.equals("")) {
			reInfo.setMessage("页大小不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (!StringUtils.isEmpty(couponType)) {
			params.put("couponType", couponType);
		}
		if (!StringUtils.isEmpty(couponName)) {
			params.put("couponName", couponName);
		}
		if (pageNum == null || pageNum.equals("")) {
			reInfo.setMessage("页码不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		PagingTool pageTool = new PagingTool(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		pageTool.setParams(params);
		int totalCount = 0;

		try {
			totalCount = (int) couponService.countTotal(pageTool);
		} catch (Exception e) {
			reInfo.setMessage("获取总数异常！");
			reInfo.setCode("-1");
			return reInfo;
		}

		if (totalCount == 0) {
			reInfo.setMessage("总数量为0！");
			reInfo.setCode("-1");
			return reInfo;
		}

		List<Coupon> couponList = null;
		try {
			couponList = couponService.getCouponList(pageTool);

		} catch (Exception e) {
			reInfo.setMessage("获取列表异常！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (couponList.size() == 0) {
			reInfo.setMessage("列表为空！");
			reInfo.setResult(new ArrayList<Coupon>());
			return reInfo;
		}
		reInfo.setCode("0");
		reInfo.setResult(couponList);
		reInfo.setTotal(totalCount);
		reInfo.setMessage("获取列表成功！");
		return reInfo;
	}
}
