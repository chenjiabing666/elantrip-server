package com.yilan.elantrip.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.yilan.elantrip.dao.RoleMapper;
import com.yilan.elantrip.domain.Role;
import com.yilan.elantrip.service.RoleService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;



@Service
public class RoleServiceImpl implements RoleService{
	
	@Resource
	private RoleMapper roleMapper;
	@Override
	public Object addRole(Role role) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		// 插入角色
		int count = roleMapper.insertSelective(role);
		if (count == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加失败");
			return resultInfo;
		}
		resultInfo.setCode("0");
		resultInfo.setMessage("添加成功");
		return resultInfo;
	}

	@Override
	public Object getRole(Integer roleId) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		Role role = null;
		try {
			role =  roleMapper.selectByPrimaryKey(roleId);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取详情异常");
		}	
		resultInfo.setCode("0");
		resultInfo.setResult(role);
		resultInfo.setMessage("获取成功");
		return resultInfo;
	}

	@Override
	public Object countTotal(PagingTool pagingTool) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = roleMapper.countTotal(pagingTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取总数异常");
		};
		return count;
	}

	@Override
	public List<Role> getRoleList(PagingTool pagingTool) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		List<Role> roleList = null;
		try {
			roleList = roleMapper.selectRoleList(pagingTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取角色列表异常");
		}

		return roleList;
	}

	@Override
	public Object modifyRole(Role role) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = roleMapper.updateByPrimaryKeySelective(role);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("修改角色异常");
		}
		resultInfo.setCode("0");
		resultInfo.setResult(count);
		resultInfo.setMessage("修改成功");
		return resultInfo;
	}

	@Override
	public List<String> getRoleNameList() throws Exception {
		
		List<String> roleNameList = null;
		try {
			roleNameList = roleMapper.getRoleNameList();
		} catch (Exception e) {
			throw new ApplicationContextException("获取部门名称列表异常ServiceImpl");
		}
		return roleNameList;
	}	
}
