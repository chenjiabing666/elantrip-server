package com.yilan.elantrip.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yilan.elantrip.domain.InterApp;
import com.yilan.elantrip.util.PagingTool;

public interface InterAppService {
        
	@Transactional
	
	/**
	 * 添加企业用户
	 * @param interApp
	 * @return
	 * @throws Exception
	 */
	Object addInterApp(InterApp interApp) throws Exception;
	
	/**
	 * 获取企业用户总数
	 * @param userId 用户Id
	 * @return
	 * @throws Exception
	 */
	Object countTotal(PagingTool pagingTool)throws Exception;
	/**
	 * 获取企业用户列表
	 * @param pagingtool 
	 * @return
	 * @throws Exception
	 */
	List<InterApp> getInterList(PagingTool pagingTool)throws Exception;
}
