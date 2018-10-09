package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.Role;
import com.yilan.elantrip.util.PagingTool;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
	// 查询总数
	int countTotal(PagingTool pagingTool);

	// 查询列表
	List<Role> selectRoleList(PagingTool pagingTool);
	
	//查询角色名列表
	List<String> getRoleNameList();
}