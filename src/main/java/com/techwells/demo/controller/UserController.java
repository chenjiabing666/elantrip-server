package com.techwells.demo.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techwells.demo.domain.User;
import com.techwells.demo.service.UserService;

@RestController
public class UserController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/user/getUser")
	public Object getUser(){
		Object user=userService.getUser(1);
		System.out.println("陈加兵");
		return user;
	}
	
	@RequestMapping("/user/test.do")
	public String test(){
		return "陈加兵";
	}
	
	
	
}
