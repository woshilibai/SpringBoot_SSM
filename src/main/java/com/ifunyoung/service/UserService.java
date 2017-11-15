package com.ifunyoung.service;

import com.ifunyoung.model.User;

public interface UserService {
	
	void addUser(User user);

	User getUser(String username);
}
