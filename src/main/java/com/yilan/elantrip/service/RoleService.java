package com.yilan.elantrip.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yilan.elantrip.domain.Role;
import com.yilan.elantrip.util.PagingTool;;

/**
 * 角色的Service
 * @author Administrator
 *
 */
@Transactional  //开启事务注解,默认运行时异常事务回滚
public interface RoleService {
	
	/**
	 * 获取角色详细信息
	 * @param roleId 角色Id
	 * @return
	 * @throws Exception
	 */
	Object getRole(Integer roleId)throws Exception;
	
	/**
	 * 添加角色
	 * @param role
	 * @return
	 * @throws Exception
	 */
	Object addRole(Role role)throws Exception;
	
	/**
	 * 修改角色信息
	 * @param role
	 * @return
	 * @throws Exception
	 */
	Object modifyRole(Role role)throws Exception;
	/**
	 * 获取角色列表
	 * 
	 * @param PagingTool
	 * @return
	 * @throws Exception
	 */
	List<Role>getRoleList(PagingTool pagingTool) throws Exception;
	
	/**
	 * 获取角色名称
	 * @return
	 * @throws Exception
	 */
	List<String> getRoleNameList() throws Exception;

	/**
	 * 获取角色总数
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Object countTotal(PagingTool pagingTool) throws Exception;

}
