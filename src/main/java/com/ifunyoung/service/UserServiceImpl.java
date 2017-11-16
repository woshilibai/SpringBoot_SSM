package com.ifunyoung.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifunyoung.dao.UserMapper;
import com.ifunyoung.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	public void addUser(User user) {
		userMapper.addUser(user);
	}

	// 模拟事务管理
	@Transactional // 表示该方法进行事务管理控制
	public void addUserTransaction(User user) {
		// 插入数据
		userMapper.addUser(user);
		// 系统报错，由于事务管理，所以会回滚sql,数据不会入库
		int i = 1 / 0;
	}

	public User getUser(String username) {
		return userMapper.queryUser(username);
	}

	@Async //表示该方法为异步方法，即开启子线程执行该方法
	@Override
	public void sendSMS() {
		System.out.println("==============2============");
	}

}
