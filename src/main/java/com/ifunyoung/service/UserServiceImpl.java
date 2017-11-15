package com.ifunyoung.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifunyoung.dao.UserMapper;
import com.ifunyoung.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	public void addUser(User user) {
		userMapper.addUser(user);
	}

	public User getUser(String username) {
		return userMapper.queryUser(username);
	}

}
