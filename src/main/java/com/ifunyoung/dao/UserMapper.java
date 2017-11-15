package com.ifunyoung.dao;

import javax.annotation.sql.DataSourceDefinition;

import org.apache.ibatis.annotations.Mapper;

import com.ifunyoung.model.User;

@Mapper //声明这个一个mapper
public interface UserMapper {
	
	void addUser(User user);
	
	User queryUser(String username);
}
