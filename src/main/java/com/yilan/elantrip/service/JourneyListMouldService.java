package com.yilan.elantrip.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yilan.elantrip.domain.JourneyListMould;
import com.yilan.elantrip.util.PagingTool;

/**
 * 行程单模板的Service
 * 
 * @author Administrator
 *
 */
@Transactional // 开启事务注解,默认运行时异常事务回滚
public interface JourneyListMouldService {

	/**
	 * 添加行程单模板
	 * 
	 * @param 
	 * @return
	 * @throws Exception
	 */
	Object addJourneyListMould(JourneyListMould journeyListMould)throws Exception;
	/**
	 * 修改行程单模板
	 * 
	 * @param 
	 * @return
	 * @throws Exception
	 */
	Object updateJourneyListMould(JourneyListMould journeyListMould)throws Exception;
	/**
	 * 获取行程单模板详细信息
	 * 
	 * @param journeyListMouldId
	 *            行程单模板Id
	 * @return
	 * @throws Exception
	 */
	Object getJourneyListMouldById(int journeyListMouldId)throws Exception;

	/**
	 * 删除行程单模板
	 * 
	 * @param journeyListMouldId
	 *            行程单模板Id
	 * @return
	 * @throws Exception
	 */
	Object DelJourneyListMouldById(int journeyListMouldId)throws Exception;
	
	/**
	 * 获取行程单模板总数
	 * 
	 * @param 
	 * @return
	 * @throws Exception
	 */
	Object countTotal(PagingTool pagingTool)throws Exception;
	/**
	 * 获取行程单模板列表
	 * 
	 * @param 
	 * @return
	 * @throws Exception
	 */
	List<JourneyListMould> getJourneyListMouldList(PagingTool pagingTool)throws Exception;
}
