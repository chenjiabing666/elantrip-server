package com.yilan.elantrip.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.yilan.elantrip.domain.Admin;
import com.yilan.elantrip.domain.Help;
import com.yilan.elantrip.service.AdminService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;

@RestController
public class AdminController {

	@Resource
	private AdminService adminService; //注入
	
	/**
	 * 添加管理员
	 * @param account 账号
	 * @param password 密码
	 * @param role_id 角色ID
	 * @param dept_id 部门ID
	 * @param email 邮箱
	 * @param mobile 电话
	 * @param activated 状态
	 * @return
	 */
	@RequestMapping("/admin/addAdmin")
	public Object addAdmin(
		 @RequestParam
		 String account,
		 String password,
		 String roleId,
		 String adminName,
		 String mobile,
		 String activated
    ) {
		 ResultInfo rsInfo=new ResultInfo();  //封装结果集
		//参数校验
			if (StringUtils.isEmpty(account)) {
				rsInfo.setCode("-1");
				rsInfo.setMessage("账号不能为空");
				return rsInfo;
			}
			if (StringUtils.isEmpty(password)) {
				rsInfo.setCode("-1");
				rsInfo.setMessage("请设置密码");
				return rsInfo;
			}
			if (StringUtils.isEmpty(roleId)) {
				rsInfo.setCode("-1");
				rsInfo.setMessage("请选择角色");
				return rsInfo;
			}
			if (StringUtils.isEmpty(adminName)) {
				rsInfo.setCode("-1");
				rsInfo.setMessage("请填写用户名");
				return rsInfo;
			}
            
			if (StringUtils.isEmpty(mobile)) {
				rsInfo.setCode("-1");
				rsInfo.setMessage("请填写手机号");
				return rsInfo;
			}
			if (StringUtils.isEmpty(activated)) {
				rsInfo.setCode("-1");
				rsInfo.setMessage("请设置状态");
				return rsInfo;
			}
			
			//数据封装
			Admin admin = new Admin();
			admin.setAccount(account);
			admin.setPassword(password);
			admin.setAdminName(adminName);
			admin.setRoleId(Integer.valueOf(roleId));
			admin.setMobile(mobile);
			admin.setCreatedDate(new Date());
			
			//调用service方法
			try {
				Object object=adminService.addAdmin(admin);
				return object;
			} catch (Exception e) {
				rsInfo.setCode("-1");
				rsInfo.setMessage("添加管理员异常---Controller");
				return rsInfo;
			}
	}
	
	/**
	 * 获取管理员详情
	 * 
	 * @param adminId
	 * 管理员编号
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/getAdmin")
	public Object getHelp(HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集
		// 获取参数
		String adminId = request.getParameter("admin_id"); // 根据Id获取管理员

		// 校验参数
		if (StringUtils.isEmpty(adminId)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("管理员列表不能为空");
			return resultInfo;
		}

		// 调用service层的方法
		try {
			Object object = adminService.getAdmin(Integer.parseInt(adminId));
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("获取管理员信息异常");
			return resultInfo;
		}
	}
	
	/**
	 * 修改管理员详情
	 * 
	 * @param admin 管理员对象
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/modifyAdmin")
	public Object modifyAdmin(
			@RequestParam
			 String adminId,
			 String account,
			 String password,
			 String roleId,
			 String adminName,
			 String mobile,
			 String activated
	    ) {
			 ResultInfo rsInfo=new ResultInfo();  //封装结果集 
	         
			//参数校验
			 Admin admin = new Admin();
				if (adminId == null || adminId.equals("")) {
					rsInfo.setMessage("adminId不能为空！");
					rsInfo.setCode("-1");
					return rsInfo;
				}
			 if (adminName != null && !adminName.equals("")){
					admin.setAdminName(adminName);
				}
			 if (account != null && !account.equals("")){
					admin.setAccount(account);
				}
			 if (roleId != null && !roleId.equals("")){
					admin.setRoleId(Integer.valueOf(roleId));
				}
			 if (password != null && !password.equals("")){
					admin.setPassword(password);
				}
			 if (mobile != null && !mobile.equals("")){
					admin.setMobile(mobile);
				}
			 if (activated != null && !activated.equals("")){
					admin.setActivated(Integer.valueOf(activated));
				}
			 admin.setAdminId(Integer.valueOf(adminId));
			 				
				try {
					Object object=adminService.modifyAdmin(admin);
					
					return object;
				} catch (Exception e) {
					rsInfo.setCode("-1");
					rsInfo.setMessage("修改管理员异常--controller");
					return rsInfo;
				}
		}
	/**
	 * 获取管理员列表
	 * @return
	 */
	@RequestMapping("/Admin/getAdminList")
	Object getDeptList(HttpServletRequest request,HttpServletResponse response){
		
		ResultInfo rsInfo = new ResultInfo();
		
		HashMap<String, Object> params = new HashMap<String, Object>();
        
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String adminName = request.getParameter("adminName");
        System.out.println(adminName);
		
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
		if (adminName != null && !adminName.equals("")) {
			params.put("adminName", adminName);
		}

		PagingTool pagingTool = new PagingTool(Integer.parseInt(pageNum),
				Integer.parseInt(pageSize));
		pagingTool.setParams(params);
		int totalCount = 0;
		try {
			totalCount = adminService.countTotal(pagingTool);
		} catch (Exception e) {
			rsInfo.setMessage("获取管理员总数异常！");
			rsInfo.setCode("-1");
			return rsInfo;
		}

		if (totalCount == 0) {
			rsInfo.setMessage("管理员数量为零");
			rsInfo.setResult(null);
			return rsInfo;
		}

		List<Admin> AdminList = null;

		
		try {
			AdminList = adminService.getAdminList(pagingTool);
		} catch (Exception e) {
			rsInfo.setMessage("获取管理员列表异常！---controller");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		
		if (AdminList.size() == 0) {
			rsInfo.setMessage("管理员列表为空！");
			rsInfo.setResult(new ArrayList<Help>());
			return rsInfo;
		}
		
		rsInfo.setResult(AdminList);
		rsInfo.setTotal(totalCount);
		rsInfo.setMessage("获取管理员列表成功！");
		return rsInfo;		
	}
	

}
