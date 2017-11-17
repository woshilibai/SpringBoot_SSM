package com.ifunyoung.controller;

import java.net.URL;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifunyoung.model.User;
import com.ifunyoung.service.UserService;
import com.ifunyoung.service.UserSpringService;


@Controller
public class IndexController {
	
	private static final Logger logger  = LoggerFactory.getLogger(IndexController.class);
	
//	@Autowired
//	private UserService userService;
	
	@Autowired
	private UserSpringService userSpringService;
	
	@Autowired //自动注入缓存管理器
	private CacheManager cacheManager;
	
	//获取系统自定义参数
	@Value("${SYSTEM.APPLICATION.URL}")
	private String applicationUrl;
	
	@Value("${SYSTEM.CURRENT.ENVIROMENT}")
	private String currEnviroment;

	@ResponseBody
	@RequestMapping("/home")
	public String home(){
		return "hello world,spring boot!";
	}
	
	@ResponseBody
	@RequestMapping("/getUser")
	public String getUser(@RequestParam("id")Integer id){
		return userSpringService.getUser(id).toString();
//		return userService.getUser(id).toString();
	}
	
	@ResponseBody
	@RequestMapping("/addUser")
	public String addUser(@RequestParam("name")String username, @RequestParam("age")Integer age){
		User user = new User(username, age);
//		userService.addUser(user);
		userSpringService.addUser(user);
		return "ok!!!!";
	}
	
	/**
	 * 查询指定id的user对象的缓存内容
	 */
	@ResponseBody
	@RequestMapping("/lookCache")
	public String lookCache(@RequestParam(required=false, value="id")String id){

		Cache cache = cacheManager.getCache("baseCache");  
        //根据缓存的key获取缓存
		List<String> keys = cache.getKeys();
		User user = null;
		for(String key:keys){
			
			logger.info("缓存内容: " + key + " = " + cache.get(key).getObjectValue().toString());
			
			if (id != null) {//请求携带参数id
				//获取指定key的缓存
				if(key.equals("userCache_" + id)){
					//如果用getValue(),则user必须实现Serializable接口，即可序列化，所以建议用getObjectValue()
//					user = (User) cache.get(key).getValue();
					user = (User) cache.get(key).getObjectValue();
				}
			}
		}
		if (user != null) {
			return user.toString();
		}
		return "请查找指定id的缓存或者该id对应key的缓存不存在";
	}
	
	/**
	 * 清空sql缓存
	 */
	@ResponseBody
	@RequestMapping("/clearCache")
	public void clearCache(){

		cacheManager.getCache("baseCache").removeAll();
	}
	
	@ResponseBody
	@RequestMapping("/updateUser")
	public String updateUser(@RequestParam("id")Integer id ,
				@RequestParam(value="name",required=false)String username, 
				@RequestParam(value="age" ,required=false)Integer age){
		User user = new User(id, username, age);
//		userService.updateUser(user);
		userSpringService.updateUser(user);
//		logger.info("after update user : " + user.toString());
		return "ok!!!!";
	}
	
	@ResponseBody
	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam("id")Integer id){
//		userService.removeUser(id);
		userSpringService.removeUser(id);
		return "ok!!!!";
	}
	
	@ResponseBody
	@RequestMapping("/addUserTransaction") //添加事务管理
	public String addUserTransaction(@RequestParam("name")String username, @RequestParam("age")Integer age){
		User user = new User(username, age);
//		userService.addUserTransaction(user);
		userSpringService.addUserTransaction(user);
		return "ok!!!!";
	}
	
	@ResponseBody
	@RequestMapping("/exceptionTest")
	public String exceptionTest(){
		int i = 1/0;
		return "success";
	}
	
	//多线程使用
	@ResponseBody
	@RequestMapping("/sendSMS")
	public String sendSMS(){
		System.out.println("==============1============");
//		userService.sendSMS();
		userSpringService.sendSMS();
		System.out.println("==============3============");
		return "success";
	}
	
	//获取系统自定义参数
	@RequestMapping("/getSysParam")
	@ResponseBody
	public String getSysParam(){
		return applicationUrl;
	}
	
	//获取系统自定义参数
	@RequestMapping("/getCurrEnviroment")
	@ResponseBody
	public String getCurrEnviroment(){
		return currEnviroment;
	}
	
//	public static void main(String[] args) {
//		SpringApplication.run(IndexController.class, args);
//	}
}
