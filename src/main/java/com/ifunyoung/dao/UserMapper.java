package com.ifunyoung.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import com.ifunyoung.model.User;

@Mapper //声明这个一个mapper
@CacheConfig(cacheNames="baseCache")//启用cache，baseCache为ehcache.xml配置的cache
public interface UserMapper {
	
	void addUser(User user);
	
	@Cacheable
	User queryUser(String username);
}
