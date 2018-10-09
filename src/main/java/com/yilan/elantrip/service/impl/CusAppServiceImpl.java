package com.yilan.elantrip.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.yilan.elantrip.dao.CustomImagesMapper;
import com.yilan.elantrip.dao.CustomTravelersMapper;
import com.yilan.elantrip.dao.CustomTripMapper;
import com.yilan.elantrip.domain.CustomImages;
import com.yilan.elantrip.domain.CustomTrip;
import com.yilan.elantrip.domain.rs.RScustomeTrip;
import com.yilan.elantrip.domain.rs.Traveler;
import com.yilan.elantrip.service.CustomerAppService;
import com.yilan.elantrip.util.PagingTool;

@Service("CustomerAppService")
public class CusAppServiceImpl implements CustomerAppService{
    
	@Resource
	private CustomTripMapper customTripMapper;
	
	@Resource
	private CustomImagesMapper customImagesMapper;
	
	@Resource
	private CustomTravelersMapper customTravelersMapper;
	
	public CustomTripMapper getCustomTripMapper() {
		return customTripMapper;
	}

	public void setCustomTripMapper(CustomTripMapper customTripMapper) {
		this.customTripMapper = customTripMapper;
	}
    /**
     * 客户端定制填写第一页
     */
	@Override
	public Object addtrip(CustomTrip customTrip) throws Exception {
			int count = 0;
			try {
				count = customTripMapper.addTripOne(customTrip);
				if (count < 0) {
					throw new Exception("填写表单二出错");
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationContextException("填写表单二异常！");
			}
			return count;
		
	}
    /**
     * 客户段定制填写第二页
     */
	@Override
	public Object addTripTwo(CustomTrip customTrip) throws Exception {
		int count = 0;
		try {
			count = customTripMapper.addTripTwo(customTrip);
			if (count < 0) {
				throw new Exception("填写表单二失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("填写表单二异常！");
		}
		return count;
	}
	
    /**
     * 客户端定制填写第三页
     */
	@Override
	public Object addTripThree(CustomTrip customTrip) throws Exception {
		int count = 0;
		try {
			count = customTripMapper.addTripThree(customTrip);
			if (count < 0) {
				throw new Exception("填写表单二失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("填写表单二异常！");
		}
		return count;
	}


    /**
     * 定制下单添加旅客
     */
	@Override
	public Object placeOrderTrav(List<Traveler> travelerList) throws Exception {
		int count = 0;
		try {
			count = customTravelersMapper.placeOrderTrav(travelerList);
			if (count < 0) {
				throw new Exception("下单时添加旅客信息失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("填写失败");
		}
		return count;
	}
    /**
     * 定制下单添加信息
     */
	@Override
	public Object placeOrder(CustomTrip customTrip) throws Exception {
		int count = 0;
		try {
			count = customTripMapper.placeOrder(customTrip);
			if (count < 0) {
				throw new Exception("下单时添加信息失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("填写失败");
		}
		return count;
	}
    
	/**
	 * 添加图片
	 */
	@Override
	public Object addImages(List<CustomImages> imagesList) throws Exception {
		int count = 0;
		try {
			count = customImagesMapper.addImages(imagesList);
			if (count < 0) {
				throw new Exception("添加图片失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("填写失败");
		}
		return count;
	}

	// 获取总数
		@Override
		public Integer countTotal(PagingTool pagingTool) {
			int count = 0;

			try {
				count = customTripMapper.countTotal(pagingTool);

			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationContextException("获取广告总数异常！");
			}
			return count;
		}
	    //获取列表
		@Override
		public List<RScustomeTrip> getCusTripList(PagingTool pagingTool) {
			List<RScustomeTrip> cusTripList = null;

			try {
				cusTripList = customTripMapper.getCusTripList(pagingTool);

			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationContextException("获取广告列表异常");
			}
			return cusTripList;
		}

}
