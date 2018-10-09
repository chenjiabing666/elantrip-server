package com.yilan.elantrip.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yilan.elantrip.domain.Help;
import com.yilan.elantrip.domain.HelpImage;
import com.yilan.elantrip.util.PagingTool;

@Transactional
public interface HelpService {
	/**
	 * 获取问题by helpId
	 * @param HelpID 问题Id
	 * @return
	 * @throws Exception
	 */
	Object getHelp(Integer helpId)throws Exception;
	
	/**
	 * 获取问题列表
	 * @param HelpID 问题Id
	 * @return
	 * @throws Exception
	 */
	List<Help> getHelpList(PagingTool pagingTool)throws Exception;
	
	/**
	 * 添加问题
	 * @param helpId
	 * @return
	 * @throws Exception
	 */
	Object addHelp(Help help,List<HelpImage> images)throws Exception;
		
	/**
	 * 删除问题
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	Integer deleteHelp(Integer helpId)throws Exception;
	
	/**
	 * 获取问题总数
	 * @param pagingTool
	 * @return
	 * @throws Exception
	 */
    Integer countTotal(PagingTool pagingTool) throws Exception;



}


