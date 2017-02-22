package com.david.service;

import org.springframework.stereotype.Service;

import com.david.facade.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public String userList() {
		return "hello world, userlist";
	}

}
