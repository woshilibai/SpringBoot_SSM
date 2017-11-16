package com.ifunyoung.service;

import com.ifunyoung.model.User;

public interface UserService {
	
	void addUser(User user);

	User getUser(Integer id);

	void addUserTransaction(User user);

	void sendSMS();
	
	void updateUser(User user);
	
	void removeUser(Integer id);
}
