package com.yilan.elantrip.service.impl;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.yilan.elantrip.dao.RateMapper;
import com.yilan.elantrip.domain.Rate;
import com.yilan.elantrip.service.RateService;
import com.yilan.elantrip.util.ResultInfo;


@Service
public class RateServiceImpl implements RateService{
    
	@Resource
	private RateMapper rateMapper;//注入
	
	
	public RateMapper getRateMapper() {
		return rateMapper;
	}


	public void setRateMapper(RateMapper rateMapper) {
		this.rateMapper = rateMapper;
	}


	@Override
	public Object modifyRate(Rate rate) throws Exception {
		int count = 0;
		try {
			count = rateMapper.updateByPrimaryKeySelective(rate);
			if (count < 0) {
				throw new Exception("修改汇率失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("修改汇率异常！");
		}
		return count;
	}


	@Override
	public Object getRate(int rate) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		Rate rate2 = null;
		try {
			rate2 = rateMapper.getRate(1);
		} catch (Exception e) {
			resultInfo.setMessage("汇率获取出错");
			resultInfo.setCode("-1");
		}
		
		return rate2;
	}

}
