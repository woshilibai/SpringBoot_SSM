package com.ifunyoung.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifunyoung.dao.UserSpringDao;
import com.ifunyoung.model.User;

@Service
public class UserSpringServiceImpl implements UserSpringService {

	@Autowired
	private UserSpringDao userSpringDao;
	
	@Override
	public void addUser(User user) {
		userSpringDao.addUser(user);
	}

	@Override
	public User getUser(Integer id) {
		return userSpringDao.queryUser(id);
	}

	@Override
	public void addUserTransaction(User user) {
		userSpringDao.addUser(user);
	}

	@Override
	public void sendSMS() {
		System.out.println("==============2============");
	}

	@Override
	public void updateUser(User user) {
		userSpringDao.updateUser(user);
	}

	@Override
	public void removeUser(Integer id) {
		userSpringDao.deleteUser(id);
	}

}
