package com.ifunyoung.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ifunyoung.model.User;
import com.ifunyoung.rowMapper.UserRowMapper;

/**
 * springboot 整合 jdbcTemplate类
 * 用于验证springboot与mybatis整合时，ehcache的实时更新缓存为null的问题
 * 
 * 结论：验证通过，即利用spring jdbc的update方法返回user对象后，即可实时正常更新到缓存，下次查询生效
 * 所以这一切缓存问题都是update返回值的问题导致。
 * @author lenovo
 *
 */
@Repository
public class UserSpringDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//特别提示：key的单引号不能少，否则会报错，被识别是一个对象;  
    public static final String CACHE_KEY = "'userCache'";  
    
    /** 
     * value属性表示使用哪个缓存策略，缓存策略在ehcache.xml 
    */  
    public static final String DEMO_CACHE_NAME = "baseCache";  
    
    //保存
    @CacheEvict(value=DEMO_CACHE_NAME, key=CACHE_KEY) 
	public void addUser(User user){
    	jdbcTemplate.update("insert into user values(null,?,?)", user.getUsername(), user.getAge());
    }
	
    //查询
	@Cacheable(value=DEMO_CACHE_NAME, key="'userCache_'+#p0") // 先从缓存中查询，如果没有则查询数据库
	public User queryUser(Integer id){
		return jdbcTemplate.queryForObject("select * from user where id = ?", new UserRowMapper(), id);
	}
	
	//修改
	@CachePut(value=DEMO_CACHE_NAME, key="'userCache_'+#p0.getId()") //更新的时候刷新到缓存,
	public User updateUser(User user){
		jdbcTemplate.update("update user set username = ?, age = ? where id = ?", user.getUsername(), user.getAge(), user.getId());
		//再查询出来，用于更新到缓存
		return jdbcTemplate.queryForObject("select * from user where id = ?", new Object[]{user.getId()}, new UserRowMapper());
	}
	
	//删除
	@CacheEvict(value="baseCache", key="'userCache_'+#p0") //清空缓存，该key的缓存不再存在
	public void deleteUser(Integer id){
		jdbcTemplate.update("delete from user where id = ?", id);
	}
}
