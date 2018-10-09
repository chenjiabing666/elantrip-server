package com.yilan.elantrip.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.yilan.elantrip.domain.Department;
import com.yilan.elantrip.domain.Help;
import com.yilan.elantrip.service.DeptService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;

@RestController
public class DeptController {

	@Resource
	private DeptService deptService;// 注入

	/**
	 * 添加部门
	 * 
	 * @param deptName
	 * @param deptDesc
	 * @param subDeptName
	 * @param upperDeptName
	 * @return
	 */
	@RequestMapping("/Department/addDepartment")
	Object addDept(@RequestParam String deptName, String deptDesc, String upperDept, String deptLevel ) {
		ResultInfo rsInfo = new ResultInfo();
		if (StringUtils.isEmpty(deptName)) {
			rsInfo.setCode("-1");
			rsInfo.setMessage("部门名称不能为空");
			return rsInfo;
		}
		if (StringUtils.isEmpty(deptDesc)) {
			rsInfo.setCode("-1");
			rsInfo.setMessage("请添加部门描述");
			return rsInfo;
		}
		if (StringUtils.isEmpty(deptLevel)) {
			rsInfo.setCode("-1");
			rsInfo.setMessage("请选择部门等级");
			return rsInfo;
		}

		// 数据封装
		Department department = new Department();
		department.setDeptName(deptName);
		department.setDeptDesc(deptDesc);
		department.setDeptLevel(Integer.valueOf(deptLevel));
		
		if (upperDept != null && !upperDept.equals("")) {
			department.setUpperDept(upperDept);
		}
		try {
			Object object = deptService.addDept(department);
			return object;
		} catch (Exception e) {
			rsInfo.setCode("-1");
			rsInfo.setMessage("添加部门异常Ccontroller");
			return rsInfo;
		}
	}

	/**
	 * 修改部门
	 * 
	 * @param deptName
	 * @param deptDesc
	 * @param subDeptName
	 * @param upperDeptName
	 * @return
	 */
	@RequestMapping("/Department/ModifyDepartment")
	Object ModifyDept(@RequestParam String deptName, String deptDesc, String deptLevel, String upperDeptName) {
		ResultInfo rsInfo = new ResultInfo();
		if (StringUtils.isEmpty(deptName)) {
			rsInfo.setCode("-1");
			rsInfo.setMessage("部门名称不能为空");
			return rsInfo;
		}
		if (StringUtils.isEmpty(deptDesc)) {
			rsInfo.setCode("-1");
			rsInfo.setMessage("请添加部门描述");
			return rsInfo;
		}
		if (StringUtils.isEmpty(deptLevel)) {
			rsInfo.setCode("-1");
			rsInfo.setMessage("请选择部门等级");
			return rsInfo;
		}
		// if (StringUtils.isEmpty(upperDeptName)) {
		// rsInfo.setCode("-1");
		// rsInfo.setMessage("请选择上级部门（选填）");
		// return rsInfo;
		// }

		// 数据封装
		Department department = new Department();
		department.setDeptName(deptName);
		if(upperDeptName!=null&!upperDeptName.equals("")) {
		department.setUpperDept(upperDeptName);
		}
		department.setDeptDesc(deptDesc);
		department.setDeptLevel(Integer.valueOf(deptLevel));

		try {
			Object object = deptService.modifyDept(department);
			return object;
		} catch (Exception e) {
			rsInfo.setCode("-1");
			rsInfo.setMessage("修改管理员异常");
			return rsInfo;
		}
	}

	/**
	 * 获取管理员详情
	 * 
	 * @param adminId
	 *            管理员编号
	 * @param request
	 * @return
	 */
	@RequestMapping("/Department/getDept")
	Object getDept(HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集
		// 获取参数
		String adminId = request.getParameter("dept_id"); // 根据Id获取管理员

		// 校验参数
		if (StringUtils.isEmpty(adminId)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("暂无任何部门，请先添加");
			return resultInfo;
		}

		// 调用service层的方法
		try {
			Object object = deptService.getDept(Integer.parseInt(adminId));
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("获取部门列表异常");
			return resultInfo;
		}
	}

	/**
	 * 获取部门列表
	 * 
	 * @return
	 */
	@RequestMapping("/Department/getDeptList")
	Object getDeptList(HttpServletRequest request, HttpServletResponse response) {

		ResultInfo rsInfo = new ResultInfo();

		HashMap<String, Object> params = new HashMap<String, Object>();

		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String deptId = request.getParameter("dept_id");

		if (pageNum == null || pageNum.equals("")) {
			rsInfo.setMessage("页码不能为空！");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		if (pageSize == null || pageSize.equals("")) {
			rsInfo.setMessage("页大小不能为空！");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		if (deptId != null && !deptId.equals("")) {
			params.put("dept_id", deptId);
		}

		PagingTool pagingTool = new PagingTool(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		int totalCount = 0;
		try {
			totalCount = deptService.countTotal(pagingTool);
		} catch (Exception e) {
			rsInfo.setMessage("获取部门总数异常！");
			rsInfo.setCode("-1");
			return rsInfo;
		}

		if (totalCount == 0) {
			rsInfo.setMessage("部门数量为零");
			rsInfo.setResult(null);
			return rsInfo;
		}

		List<Department> helpList = null;
		try {
			helpList = deptService.getDeptList(pagingTool);
		} catch (Exception e) {
			rsInfo.setMessage("获取部门列表异常！");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		if (helpList.size() == 0) {
			rsInfo.setMessage("部门列表为空！");
			rsInfo.setResult(new ArrayList<Help>());
			return rsInfo;
		}
		rsInfo.setResult(helpList);
		rsInfo.setTotal(totalCount);
		rsInfo.setMessage("获取部门列表成功！");
		return rsInfo;
	}

	/**
	 * 获取部门名称列表
	 * 
	 * @return
	 */
	@RequestMapping("/department/getDeptNameList")
	Object getDeptNameList(HttpServletRequest request, HttpServletResponse response) {
		ResultInfo rsInfo = new ResultInfo();
		List<String> deptNameList = null;
		try {
			deptNameList = deptService.getDeptNameList();
		} catch (Exception e) {
			rsInfo.setMessage("获取部门名称列表异常");
			rsInfo.setCode("-1");
			rsInfo.setResult(null);
			return rsInfo;
		}
		rsInfo.setResult(deptNameList);
		rsInfo.setCode("0");
		rsInfo.setMessage("获取部门名称列表成功！");
		return rsInfo;
	}
	
	@RequestMapping("/department/getSubDeptList")
	Object getSubDeptList(HttpServletRequest request, HttpServletResponse response) {
		
		String upperDept = request.getParameter("upperDept");
        Department department = new Department();
		ResultInfo resultInfo = new ResultInfo();
		if (StringUtils.isEmpty(upperDept)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("暂无任何上级部门，请先选择");
			return resultInfo;
		}else {
			department.setUpperDept(upperDept);
		}
		
		List<String> subDeptList = null;
		try {
			subDeptList = deptService.getSubdeptList(department);
		} catch (Exception e) {
			resultInfo.setMessage("获取子部门名称列表异常");
			resultInfo.setCode("-1");
			resultInfo.setResult(null);
			return resultInfo;
		}
		resultInfo.setResult(subDeptList);
		resultInfo.setCode("0");
		resultInfo.setMessage("获取子部门名称列表成功！");
		return resultInfo;
	}
}
