package com.ifunyoung.aop;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * @ClassName: WebReqLogAspect
 * @Description: TODO(打印web请求的请求参数和响应结果)
 * @author lenovo
 * @date 2017-11-15 上午10:11:45
 * 
 */
@Aspect
// 定义一个切面类
@Component
// 注入到spring容器中
public class WebReqLogAspect {

	private Logger logger = LoggerFactory.getLogger(WebReqLogAspect.class);

	// 定义切入点表达式
	/**
	 * 1.第一个*：表示任意返回值类型 2.第二个*：表示任意包名 3.第三个*：表示任意方法名称
	 * 
	 * 第一个.. : 表示任意多层子包 第二个.. : 表示任意参数列表（参数类型以及参数个数）
	 */
	@Pointcut("execution(public * com.ifunyoung.controller..*.*(..))")
	public void webLog() {

	}

	/*
	 * 重中之重
	 * 这里的JoinPoint为org.aspectj.lang.JoinPoint，而并非Joinpoint(org.aopalliance.
	 * intercept.Joinpoint)， 这是两个jar中的两个类，写错的话会一直提示pointcut表达式异常
	 */
	// before增强织入
	@Before("webLog()")
	public void before(JoinPoint joinPoint) {
		// 获取httpRequest
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		// 打印请求内容以及入参
		logger.info("URL : " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		// 打印响应请求的处理类，处理方法，接受的参数
		logger.info("CLASS_METHOD : "
				+ joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();// 参数名
			String paramValue = request.getParameter(paramName);// 参数值
			logger.info("requestParam : " + paramName + "=" + paramValue);
		}
	}

	// afterReturning增强织入
	@AfterReturning(pointcut = "webLog()", returning = "obj")
	public void afterReturning(Object obj) {
		// 处理完web请求后，打印返回结果
		logger.info("response : " + obj);
	}
}
