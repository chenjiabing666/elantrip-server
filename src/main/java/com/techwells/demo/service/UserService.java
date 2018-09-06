package com.techwells.demo.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional   //事务管理
public interface UserService {
	Object getUser(Integer userId);
}
