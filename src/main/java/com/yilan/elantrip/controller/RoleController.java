package com.yilan.elantrip.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.yilan.elantrip.domain.Role;
import com.yilan.elantrip.service.RoleService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;

/**
 * 角色的Controller
 * 
 * @author Administrator
 *
 */
@RestController // 直接使用@RestController，不用@ResponseBody
public class RoleController {

	@Resource
	private RoleService roleService; // 注入

	/**
	 * 添加角色
	 * 
	 * @param roleName
	 *            角色名
	 * @param deptName
	 *            部门名
	 * @param description
	 *            描述
	 * @param activated
	 *            状态
	 * @return
	 */
	@RequestMapping("/role/addRole")
	public Object addMessage(String roleName, String deptName, String description, String activated) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集

		// 校验参数
		if (StringUtils.isEmpty(roleName)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("角色名不能为空");
			return resultInfo;
		}

		if (StringUtils.isEmpty(deptName)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("部门名不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(description)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("描述不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(activated)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("状态不能为空");
			return resultInfo;
		}

		// 封装数据
		Role role = new Role();
		role.setActivated(Integer.parseInt(activated));
		role.setRoleName(roleName);
		role.setDescription(description);
		role.setDeptName(deptName);
		role.setCreatedDate(new Date());
		// 调用service层的方法
		try {
			Object object = roleService.addRole(role);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加角色异常");
			return resultInfo;
		}
	}

	/**
	 * 获取角色详情
	 * 
	 * @param roleId
	 *            角色Id
	 * @param request
	 * @return
	 */
	@RequestMapping("/role/getRole")
	public Object getMessage(HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo();
		String roleId = request.getParameter("roleId");
		// 校验参数
		if (StringUtils.isEmpty(roleId)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("角色Id不能为空");
			return resultInfo;
		}

		// 调用service层的方法
		try {
			Object object = roleService.getRole(Integer.parseInt(roleId));
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("获取异常");
			return resultInfo;
		}

	}

	/**
	 * 获取角色列表 
	 * 
	 * @param pageNum
	 *            起始页
	 * @param pageSize
	 *            每页显示数量
	 * @param roleName
	 *            角色名称
	 * @return
	 */
	@RequestMapping(value = "/role/getRoleList")
	public @ResponseBody Object getNewsList(HttpServletRequest request, HttpServletResponse response) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集

		HashMap<String, Object> params = new HashMap<String, Object>();
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String roleName = request.getParameter("roleName");
		if (pageNum == null || pageNum.equals("")) {
			reInfo.setMessage("页码不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (pageSize == null || pageSize.equals("")) {
			reInfo.setMessage("页大小不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (!StringUtils.isEmpty(roleName)) {
			params.put("roleName", roleName);
		}
		PagingTool pageTool = new PagingTool(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		pageTool.setParams(params);
		int totalCount = 0;
		try {
			totalCount = (int) roleService.countTotal(pageTool);
		} catch (Exception e) {
			reInfo.setMessage("获取总数异常！");
			reInfo.setCode("-1");
			return reInfo;
		}

		if (totalCount == 0) {
			reInfo.setMessage("总数量为0！");
			reInfo.setCode("-1");
			return reInfo;
		}

		List<Role> roles = null;
		try {
			roles = roleService.getRoleList(pageTool);
		} catch (Exception e) {
			reInfo.setMessage("获取列表异常！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (roles.size() == 0) {
			reInfo.setMessage("列表为空！");
			reInfo.setResult(new ArrayList<Role>());
			return reInfo;
		}
		reInfo.setResult(roles);
		reInfo.setTotal(totalCount);
		reInfo.setCode("0");
		reInfo.setMessage("获取列表成功！");
		return reInfo;
	}

	/**
	 * 修改角色
	 * 
	 * @param roleName
	 *            角色名
	 * @param deptName
	 *            部门名
	 * @param description
	 *            描述
	 * @param activated
	 *            状态
	 * @return
	 */
	@RequestMapping("/role/modifyRole")
	public Object modifyRole(String roleId, String roleName, String deptName, String description, String activated) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集
		// 封装数据
		Role role = new Role();
		// 校验参数

		if (StringUtils.isEmpty(roleId)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("角色id不能为空");
			return resultInfo;
		}
		role.setRoleId(Integer.parseInt(roleId));
		if (!StringUtils.isEmpty(roleName)) {
			role.setRoleName(roleName);
		}

		if (!StringUtils.isEmpty(deptName)) {
			role.setDeptName(deptName);
		}
		if (!StringUtils.isEmpty(description)) {
			role.setDescription(description);
		}
		if (!StringUtils.isEmpty(activated)) {
			role.setActivated(Integer.parseInt(activated));
		}
		// 调用service层的方法
		Object object = null;
		try {
			object = roleService.modifyRole(role);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			resultInfo.setMessage("修改角色异常");
			return resultInfo;
		}
	}
	
	/**
	 * 获取角色名称列表
	 * 
	 * @return
	 */
	@RequestMapping("/role/getRoleNameList")
	Object getRoleNameList(HttpServletRequest request, HttpServletResponse response) {
		ResultInfo rsInfo = new ResultInfo();
		List<String> roleNameList = null;
		try {
			roleNameList = roleService.getRoleNameList();
		} catch (Exception e) {
			rsInfo.setMessage("获取角色名称列表异常Controller");
			rsInfo.setCode("-1");
			rsInfo.setResult(null);
			return rsInfo;
		}
		rsInfo.setResult(roleNameList);
		rsInfo.setCode("0");
		rsInfo.setMessage("获取角色名称列表成功！");
		return rsInfo;
	}
}
