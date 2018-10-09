package com.yilan.elantrip.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.yilan.elantrip.dao.AdminMapper;
import com.yilan.elantrip.domain.Admin;
import com.yilan.elantrip.service.AdminService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {
	
	@Resource
	private AdminMapper adminMapper;
    
	
	public AdminMapper getAdminMapper() {
		return adminMapper;
	}

	public void setAdminMapper(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}
    //获取信息
	@Override
	public Object getAdmin(Integer adminId) throws Exception {
		Admin admin = null;
		try {
		admin = adminMapper.selectByPrimaryKey(adminId);
		}
		catch (Exception e) {
		   throw new Exception("获取用户信息异常");
		}
		return admin;
	}
    //添加信息
	@Override
	public Object addAdmin(Admin admin) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = adminMapper.insertSelective(admin);
		if(count==0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加管理员失败");
		}else {
			resultInfo.setCode("0");
			resultInfo.setMessage("添加管理员成功");
		}
		return resultInfo;
	}
    //修改信息
	@Override
	public Object modifyAdmin(Admin admin) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = adminMapper.updateByPrimaryKeySelective(admin);
		if(count==0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("修改管理员失败");
		}
		else {
			resultInfo.setCode("0");
			resultInfo.setMessage("修改管理员成功");
		}
		return resultInfo;
	}

	@Override
		public Integer countTotal(PagingTool pagingTool) throws Exception {
			int count = 0;

			try {
				count = adminMapper.countTotal(pagingTool);
            System.out.print(count);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationContextException("获取管理员总数失败---ServiceImpl！");
			}
			return count;
		}
	
	
		@Override
		public List<Admin> getAdminList(PagingTool pagingTool) throws Exception {
			
			List<Admin> deptList = null;

			try {
				deptList = adminMapper.selectAdmintList(pagingTool);
				System.out.println(pagingTool.getParams().get("adminName")+"!!!!");
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationContextException("获取管理员列表异常");
			}
			return deptList;
		
		}

}
