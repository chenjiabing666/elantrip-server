package com.yilan.elantrip.service;

import org.springframework.transaction.annotation.Transactional;

import com.yilan.elantrip.domain.Rate;

@Transactional // 开启事务注解,默认运行时异常事务回滚
public interface RateService {
     
	Object modifyRate(Rate rate) throws Exception;
	
	Object getRate(int rate) throws Exception;
	
}
