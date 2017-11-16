package com.ifunyoung.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.ifunyoung.model.User;

@Mapper //声明这个一个mapper
@CacheConfig(cacheNames="baseCache")//启用cache，baseCache为ehcache.xml配置的cache
public interface UserMapper {
	
	 //特别提示：key的单引号不能少，否则会报错，被识别是一个对象;  
    public static final String CACHE_KEY = "'userCache'";  
    
    /** 
     * value属性表示使用哪个缓存策略，缓存策略在ehcache.xml 
    */  
    public static final String DEMO_CACHE_NAME = "baseCache";  
    
    //保存
    @CacheEvict(value=DEMO_CACHE_NAME, key=CACHE_KEY) 
	void addUser(User user);
	
    //@Cacheable(value=DEMO_CACHE_NAME,key="'userCache_'+#id") 我这里#id总是null，#user.getId()直接报错空指针异常
    //查询
	@Cacheable(value=DEMO_CACHE_NAME, key="'userCache_123'") // 先从缓存中查询，如果没有则查询数据库
	User queryUser(Integer id);
	
	/**
	 * 这个动态mapper的update不能返回更新的对象user，所以更新到缓存里的为null，导致下次查询时，会从缓存获取null,所以动态mapper实现类与ehcache整合的一点小问题,只能手动清空缓存
	 * @param user
	 * 
	 * 这个表达式有问题，所以暂时将缓存的key写死
	 * @CachePut(value = DEMO_CACHE_NAME,key = "'userCache_'+#user.getId()") 
	 */
	//修改
	@CachePut(value=DEMO_CACHE_NAME, key="'userCache_123'") //更新的时候刷新到缓存
	void updateUser(User user);
	
	/**
	 * 删除可以正常清空缓存
	 * @param id
	 * 
	 * @CacheEvict(value = DEMO_CACHE_NAME,key = "'userCache_'+#id")
	 */
	//删除
	@CacheEvict(value="baseCache", key="'userCache_123'") //清空缓存
	void deleteUser(Integer id);
	
}
