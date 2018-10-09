package com.yilan.elantrip.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.ast.SQLKeep.DenseRank;
import com.yilan.elantrip.dao.DepartmentMapper;
import com.yilan.elantrip.domain.Department;
import com.yilan.elantrip.service.DeptService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;

@Service("DeptService")
public class DeptServiceImpl implements DeptService {
	
    @Resource
	private DepartmentMapper detpMapper;
    
    
	public DepartmentMapper getDetpMapper() {
		return detpMapper;
	}
	public void setDetpMapper(DepartmentMapper detpMapper) {
		this.detpMapper = detpMapper;
	}
		//获取信息
		@Override
		public Object getDept(Integer DeptId) throws Exception {
			Department dept = null;
			try {
				dept = detpMapper.selectByPrimaryKey(DeptId);
			}
			catch (Exception e) {
			   throw new Exception("获取用户信息异常");
			}
			return dept;
		}
		
		
	    //添加信息
		@Override
		public Object addDept(Department dept) throws Exception {
			ResultInfo resultInfo = new ResultInfo();
			int count = detpMapper.insertSelective(dept);
			if(count==0) {
				resultInfo.setCode("-1");
				resultInfo.setMessage("添加管理员失败");
			}else {
				resultInfo.setMessage("添加管理员成功");
			}
			return resultInfo;
		}
	    //修改信息
		@Override
		public Object modifyDept(Department dept) throws Exception {
			ResultInfo resultInfo = new ResultInfo();
			int count = detpMapper.updateByPrimaryKeySelective(dept);
			if(count==0) {
				resultInfo.setCode("-1");
				resultInfo.setMessage("修改管理员失败");
			}
			else {
				resultInfo.setMessage("修改管理员成功");
			}
			return resultInfo;
		}
		@Override
		public Integer countTotal(PagingTool pagingTool) throws Exception {
			int count = 0;

			try {
				count = detpMapper.countTotal(pagingTool);
            System.out.print(count);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationContextException("获取部门总数失败---ServiceImpl！");
			}
			return count;
		}
		@Override
		public List<Department> getDeptList(PagingTool pagingTool) throws Exception {
			List<Department> deptList = null;

			try {
				deptList = detpMapper.selectDeptList(pagingTool);

			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationContextException("获取部门列表异常");
			}
			return deptList;
		
		}
		
		@Override
		public List<String> getDeptNameList() throws Exception {
			List<String> deptNameList = null;
			try {
				deptNameList = detpMapper.selectDeptNameList();
			} catch (Exception e) {
				throw new ApplicationContextException("获取部门名称列表异常");
			}
			return deptNameList;
		}
		
		@Override
		public List<String> getSubdeptList(Department department) throws Exception {
			List<String> subdeptList = null;
			try {
				subdeptList = detpMapper.selectSubdeptList(department);
			} catch (Exception e) {
				throw new ApplicationContextException("获取部门名称列表异常");
			}
			return subdeptList;
		
		}

	}