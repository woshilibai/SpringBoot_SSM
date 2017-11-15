package com.ifunyoung.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifunyoung.model.User;
import com.ifunyoung.service.UserService;


@Controller
public class IndexController {
	
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping("/home")
	public String home(){
		return "hello world,spring boot!";
	}
	
	@ResponseBody
	@RequestMapping("/getUser")
	public String getUser(@RequestParam("name")String username, @RequestParam("age")Integer age){
		return userService.getUser(username).toString();
	}
	
	@ResponseBody
	@RequestMapping("/addUser")
	public String addUser(@RequestParam("name")String username, @RequestParam("age")Integer age){
		User user = new User(username, age);
		userService.addUser(user);
		return "ok!!!!";
	}
	
	@ResponseBody
	@RequestMapping("/addUserTransaction") //添加事务管理
	public String addUserTransaction(@RequestParam("name")String username, @RequestParam("age")Integer age){
		User user = new User(username, age);
		userService.addUserTransaction(user);
		return "ok!!!!";
	}
	
	@ResponseBody
	@RequestMapping("/exceptionTest")
	public String exceptionTest(){
		int i = 1/0;
		return "success";
	}
	
//	public static void main(String[] args) {
//		SpringApplication.run(IndexController.class, args);
//	}
}
