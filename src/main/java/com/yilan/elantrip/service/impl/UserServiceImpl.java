package com.yilan.elantrip.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.yilan.elantrip.dao.UserLinkTravllerMapper;
import com.yilan.elantrip.dao.UserMapper;
import com.yilan.elantrip.domain.User;
import com.yilan.elantrip.domain.UserLinkTravller;
import com.yilan.elantrip.domain.rs.RSuserOrder;
import com.yilan.elantrip.service.UserService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper; // 注入dao
	@Resource
	private UserLinkTravllerMapper userLinkTravllerMapper;

	@Override
	public Object getUser(Integer userId) throws Exception {
//		ResultInfo resultInfo = new ResultInfo();
		List<Object> result = new ArrayList<>();
		User user = null;
		user = userMapper.selectUserDetail(userId);
		Integer companyLevel = user.getCompanyLevel();
		Integer changerCompanyLevel = user.getChangedCompanyLevel();
		Date startTime = user.getStartTime();
		Date date = new Date();
		if (changerCompanyLevel!=null&&changerCompanyLevel!=companyLevel&&startTime!=null&&startTime.before(date)) {
			user.setCompanyLevel(changerCompanyLevel);
			user.setUpdatedDate(date);
			userMapper.updateByPrimaryKeySelective(user);
		}
		List<RSuserOrder> orders = null;
		orders = userMapper.getUserOrderByUserId(userId);
		result.add(user);
		result.add(orders);
		return result;
	}

	@Override
	public Object addUser(User user) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = userMapper.insertSelective(user);
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
	public Object modifyUser(User user) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = userMapper.updateByPrimaryKeySelective(user);

		if (count == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("修改失败");
			return resultInfo;
		}
        resultInfo.setCode("0");
		resultInfo.setMessage("修改成功");

		return resultInfo;
	}

	@Override
	public Object deleteUser(Integer userId) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = userMapper.deleteByPrimaryKey(userId);

		if (count == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("删除失败");
			return resultInfo;
		}
		resultInfo.setCode("0");
		resultInfo.setMessage("删除成功");
		return resultInfo;
	}

	@Override
	public User getUserByMobile(String mobile) throws Exception{
		User user = null;
		user = userMapper.selectByMobile(mobile);
		return user;
	}

	@Override
	public User getUserByEmail(String email) throws Exception{
		User user = null;
		user = userMapper.selectByEmail(email);
		return user;
	}

	@Override
	public Object login(HashMap<String, String> params) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		System.out.println(params.get("mobile"));
		System.out.println(params.get("userName"));
		System.out.println(params.get("email"));
		User user = userMapper.login(params);
		System.out.println("223123123123"+user);
		if (user == null) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("该用户不存在");
			return resultInfo;
		}

		// 如果用户存在，验证密码

		if (!user.getPassword().equals(params.get("password"))) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("密码错误");
			return resultInfo;
		}

		user.setActivated(3); // 已登录
		user.setLastLogin(new Date()); // 最后登录时间

		userMapper.updateByPrimaryKeySelective(user); // 更新数据

		user.setPassword("**********");

		// 密码正确，登录成功
		resultInfo.setCode("0");
		resultInfo.setMessage("登录成功");
		return resultInfo;
	}

	@Override
	public Object countTotal(PagingTool pagingTool) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = userMapper.countTotal(pagingTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取总数异常");
		}
		return count;
	}

	@Override
	public List<User> getPersonalUserList(PagingTool pagingTool) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		List<User> userList = null;
		try {
			userList = userMapper.selectPersonalUserList(pagingTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取用户列表异常");
		}

		return userList;
	}

	@Override
	public List<User> getCompanyUserList(PagingTool pagingTool) throws Exception {
		ResultInfo resultInfo = new ResultInfo();		
		List<User> userList = null;
		try {
			userList = userMapper.selectCompanyUserList(pagingTool);
			for (User user : userList) {
				User user2 = null;
				user2 = userMapper.getCompanyLevelCorrelation(user.getUserId());
				Integer companyLevel = user2.getCompanyLevel();
				Integer changerCompanyLevel = user2.getChangedCompanyLevel();
				Date startTime = user2.getStartTime();
				Date date = new Date();
				if (changerCompanyLevel!=null&&changerCompanyLevel!=companyLevel&&startTime!=null&&startTime.before(date)) {
					user.setCompanyLevel(changerCompanyLevel);
					user.setUpdatedDate(date);
					userMapper.updateByPrimaryKeySelective(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取用户列表异常");
		}

		return userList;
	}

	@Override
	public Object activateUser(String email) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		int count=0;
		try {
			count = userMapper.modifyUserActivatedByEmail(email);
			if (count < 0) {
				resultInfo.setCode("-1");
				throw new Exception("激活用户失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("激活用户异常");
		}
		return count;
	}

	@Override
	public Object addUserLinkOrTravller(List<UserLinkTravller> userLinkTravllers) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		int count=0;
		try {
			count = userLinkTravllerMapper.insertBatchUserLinkTravller(userLinkTravllers);
			if (count < 0) {
				resultInfo.setCode("-1");
				throw new Exception("添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("添加失败");
		}
		resultInfo.setCode("0");
		resultInfo.setMessage("添加成功");
		return resultInfo;
	}

	@Override
	public Object userLinkcountTotal(PagingTool pagingTool) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = userLinkTravllerMapper.countTotal(pagingTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取总数异常");
		}
		return count;
	}

	@Override
	public List<UserLinkTravller> getUserLinkList(PagingTool pagingTool) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		List<UserLinkTravller> userLinkTravllerList = null;
		try {
			userLinkTravllerList = userLinkTravllerMapper.selectUserLinkList(pagingTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取联系人列表异常");
		}

		return userLinkTravllerList;
	}

	@Override
	public User getUserByUserName(String userName) throws Exception{
		User user = null;

		user = userMapper.selectUserByUserName(userName);

		return user;
	}

}
