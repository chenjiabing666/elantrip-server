package com.yilan.elantrip.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yilan.elantrip.domain.Rate;
import com.yilan.elantrip.service.RateService;
import com.yilan.elantrip.util.ResultInfo;

@RestController
public class RateController {
	
	@Resource
    private RateService rateService;
   
    @RequestMapping("/Rate/modifyRate")
    Object modifyRate(@RequestParam String newRate) {
    	
    	ResultInfo reInfo = new ResultInfo(); 
    	if (newRate == null || newRate.equals("")) {
			reInfo.setMessage("汇率不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
//    	数据封装
    	Rate rate = new Rate();
    	rate.setRate(Double.valueOf(newRate));
    	rate.setRateId(1);
    	try {
			Object object = rateService.modifyRate(rate);
			return object;
		} catch (Exception e) {
			reInfo.setCode("-1");
			reInfo.setMessage("汇率修改异常");
			return reInfo;
		}	
    }
    
    @RequestMapping("/Rate/getRate")
    Object getRate() {
    	
    	ResultInfo reInfo = new ResultInfo(); 

//    	数据封装
    	Rate rate = new Rate();
    	rate.setRateId(1);
    	try {
			Object object = rateService.getRate(1);
			return object;
		} catch (Exception e) {
			reInfo.setCode("-1");
			reInfo.setMessage("汇率修改异常");
			return reInfo;
		}	
    }
}
