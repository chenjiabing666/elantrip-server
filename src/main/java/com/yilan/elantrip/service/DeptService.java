package com.yilan.elantrip.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yilan.elantrip.domain.Department;
import com.yilan.elantrip.util.PagingTool;

@Transactional
public interface DeptService {
	/**
	 * 添加部门
	 * @param department
	 * @return
	 * @throws Exception
	 */
	Object addDept(Department department) throws Exception; 
	
	/**
	 * 修改部门
	 * @param department
	 * @return
	 * @throws Exception
	 */
	Object modifyDept(Department department) throws Exception;
	
	/**
	 * 获取部门信息
	 * @param deptId
	 * @return
	 * @throws Exception
	 */
	Object getDept(Integer deptId) throws Exception;
    
	/**
	 * 获取部门总数
	 * @param userId 用户Id
	 * @return
	 * @throws Exception
	 */
	Integer countTotal(PagingTool pagingTool)throws Exception;
	/**
	 * 获取部门列表
	 * @param pagingtool 
	 * @return
	 * @throws Exception
	 */
	List<Department> getDeptList(PagingTool pagingTool)throws Exception;
	/**
	 * 获取部门名称列表
	 * @param pagingtool 
	 * @return
	 * @throws Exception
	 */
	List<String> getDeptNameList()throws Exception;
	
	/**
	 * 获取部门名称列表
	 * @param pagingtool 
	 * @return
	 * @throws Exception
	 */
	List<String> getSubdeptList(Department department)throws Exception;
	
	
}
