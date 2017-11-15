package com.ifunyoung.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
 
/**
 * 
 * @ClassName: ScheduerJob
 * @Description: TODO(springboot集成定时job)
 * @author lenovo
 * @date 2017-11-15 下午2:07:31
 * 
 */
@Component
// 注入到spring容器
public class ScheduerJob {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Scheduled(cron = "0 0/1 * * * ?") // 每1分钟执行一次
	public void schedueTask() {
		logger.info("-------------定时任务执行中--------------");
	}
}
