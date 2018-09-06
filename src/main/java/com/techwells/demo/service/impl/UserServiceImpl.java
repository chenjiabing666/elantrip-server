package com.techwells.demo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.techwells.demo.dao.UserMapper;
import com.techwells.demo.domain.User;
import com.techwells.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserMapper userMapper;
	
	@Override
	public Object getUser(Integer userId) {
		User user=userMapper.selectByPrimaryKey(userId);
		return user;
	}
		
}
