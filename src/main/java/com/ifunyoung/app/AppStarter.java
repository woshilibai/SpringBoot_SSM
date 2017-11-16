package com.ifunyoung.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration //spring boot 启动总开关注解
@ComponentScan(basePackages={"com.ifunyoung"}) //扫描包路径
@MapperScan(basePackages={"com.ifunyoung.dao"}) //扫描mapper
@EnableScheduling //增加支持定时任务注解
//@EnableCaching //开启缓存注解
@EnableAsync //开启多线程支持
public class AppStarter {

	public static void main(String[] args) {
		SpringApplication.run(AppStarter.class, args);
	}

}
