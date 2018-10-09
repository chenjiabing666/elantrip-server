package com.yilan.elantrip.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.yilan.elantrip.domain.CustomImages;
import com.yilan.elantrip.domain.CustomTrip;
import com.yilan.elantrip.domain.rs.RScustomeTrip;
import com.yilan.elantrip.domain.rs.Traveler;
import com.yilan.elantrip.util.PagingTool;

@Transactional
public interface CustomerAppService {
	
	/**
	 * 客户填写第一页
	 * @param customTrip
	 * @return
	 * @throws Exception
	 */
	Object addtrip(CustomTrip customTrip) throws Exception;
	
	/**
	 * 客户填写第二页
	 * @param customTrip
	 * @return
	 * @throws Exception
	 */
	Object addTripTwo(CustomTrip customTrip) throws Exception;
	
	/**
	 * 客户填写第三页
	 * @param customTrip
	 * @return
	 * @throws Exception
	 */
	Object addTripThree(CustomTrip customTrip) throws Exception;
	
	/**
	 * 旅行团体信息
	 * @param customTrip
	 * @return
	 * @throws Exception
	 */
	Object placeOrder(CustomTrip customTrip) throws Exception;
	
	/**
	 * 旅客信息填写
	 * @param travelerList
	 * @return
	 * @throws Exception
	 */
	Object placeOrderTrav(@Param(value="travelerList") List<Traveler> travelerList) throws Exception;
	
	/**
	 * 上传详情图片
	 * @param imagesList
	 * @return
	 * @throws Exception
	 */
	
	Object addImages(List<CustomImages> imagesList) throws Exception;
	
	/**
	 * 获取总数
	 * @param pagingTool
	 * @return
	 * @throws Exception
	 */
	Integer countTotal(PagingTool pagingTool) throws Exception;
	
	/**
	 * 获取列表
	 * @param pagingTool
	 * @return
	 * @throws Exception
	 */
	List<RScustomeTrip> getCusTripList(PagingTool pagingTool) throws Exception;
	

	
}
