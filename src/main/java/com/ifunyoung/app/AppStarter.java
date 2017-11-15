package com.ifunyoung.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration //spring boot 启动总开关注解
@ComponentScan(basePackages={"com.ifunyoung.controller","com.ifunyoung.service"}) //扫描包路径
@MapperScan(basePackages={"com.ifunyoung.dao"}) //扫描mapper
public class AppStarter {

	public static void main(String[] args) {
		SpringApplication.run(AppStarter.class, args);
	}

}
