package com.yilan.elantrip.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yilan.elantrip.domain.User;
import com.yilan.elantrip.domain.UserLinkTravller;
import com.yilan.elantrip.util.PagingTool;

/**
 * 用户的Service
 * 
 * @author Administrator
 *
 */
@Transactional // 开启事务注解,默认运行时异常事务回滚
public interface UserService {

	/**
	 * 获取用户详细信息
	 * 
	 * @param userId
	 *            用户Id
	 * @return
	 * @throws Exception
	 */
	Object getUser(Integer userId) throws Exception;

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Object addUser(User user) throws Exception;
	/**
	 * 添加用户常用联系人
	 * 
	 * @param 
	 * @return
	 * @throws Exception
	 */
    Object addUserLinkOrTravller(List<UserLinkTravller> userLinkTravllers) throws Exception;
	/**
	 * 修改用户信息
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Object modifyUser(User user) throws Exception;

	/**
	 * 获取用户总数
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Object countTotal(PagingTool pagingTool) throws Exception;

	/**
	 * 获取个人用户列表
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<User> getPersonalUserList(PagingTool pagingTool) throws Exception;

	/**
	 * 删除用户
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	Object deleteUser(Integer userId) throws Exception;

	User getUserByMobile(String mobile) throws Exception;

	User getUserByEmail(String email) throws Exception;
	
	User getUserByUserName(String userName) throws Exception;

	Object login(HashMap<String, String> params) throws Exception;
	/**
	 * 获取企业用户列表
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<User> getCompanyUserList(PagingTool pagingTool) throws Exception;
	/**
	 * 激活用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Object activateUser(String email) throws Exception;
	/**
	 * 获取保险总数
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Object userLinkcountTotal(PagingTool pagingTool)throws Exception;
	/**
	 * 获取保险列表
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<UserLinkTravller> getUserLinkList(PagingTool pagingTool)throws Exception;
}
