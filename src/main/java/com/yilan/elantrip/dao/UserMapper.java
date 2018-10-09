package com.yilan.elantrip.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yilan.elantrip.domain.User;
import com.yilan.elantrip.domain.rs.RSuserOrder;
import com.yilan.elantrip.util.PagingTool;

public interface UserMapper {
	int deleteByPrimaryKey(Integer userId);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer userId);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	User selectByMobile(String mobile);

	User selectByEmail(String email);

	User selectUserByUserName(String userName);

	// 查询总数
	int countTotal(PagingTool pagingTool);

	// 查询个人列表
	List<User> selectPersonalUserList(PagingTool pagingTool);

	// 查询企业列表
	List<User> selectCompanyUserList(PagingTool pagingTool);

	// 获取详情
	User selectUserDetail(int usreId);

	// 根据用户id查找用户电话
	String[] getUserIdByUserType(String userType);
	
	// 根据用户ID查找订单
	List<RSuserOrder> getUserOrderByUserId(Integer userId);
	
	// 根据邮箱修改用户状态
	int modifyUserActivatedByEmail(String email);
	
	// 登录
	User login(HashMap<String, String> params);
	
	// 根据用户名称找到用户列表
	List<User> getUserListByUserNames(@Param(value="userNames")String userNames);
	
	// 根据临时用户类型找到用户列表
	List<User> getUserListByUserTempType(@Param(value="userTempTypes")String userTempTypes);
	
	// 查询企业用户当前等级相关
	User getCompanyLevelCorrelation(Integer userId);

}