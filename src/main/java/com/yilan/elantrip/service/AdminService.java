package com.yilan.elantrip.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yilan.elantrip.domain.Admin;
import com.yilan.elantrip.util.PagingTool;

@Transactional
public interface AdminService {
	/**
	 * 获取管理员列表
	 * @param adminId 管理员Id
	 * @return
	 * @throws Exception
	 */
	Object getAdmin(Integer AdminId)throws Exception;
	
	/**
	 * 添加管理员
	 * @param admin 管理员对象
	 * @return
	 * @throws Exception
	 */
	Object addAdmin(Admin admin)throws Exception;
		
	/**
	 * 修改管理员
	 * @param admin 管理员对象
	 * @return
	 * @throws Exception
	 */
	Object modifyAdmin(Admin admin)throws Exception;
	
	
    /**
     * 获取管理员总数	
     * @param pagingTool
     * @return
     */
	Integer countTotal(PagingTool pagingTool)throws Exception;
	
	/**
	 * 获取管理员列表
	 */
	List<Admin> getAdminList(PagingTool pagingTool) throws Exception;


}

