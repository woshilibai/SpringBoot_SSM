package com.ifunyoung.exceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @ClassName: GlobalExceptionHandler 
 * @Description: TODO(全局异常处理类，用于拦截系统所有异常) 
 * @author lenovo
 * @date 2017-11-15 下午2:36:55 
 *
 */
//@ControllerAdvice //定义异常拦截切面,注释掉是为了方便调试查看异常信息
public class GlobalExceptionHandler {

	@ResponseBody //表示拦截所有返回结果是json类型的返回
	@ExceptionHandler(RuntimeException.class)//表示拦截运行时异常
	public Map<String,Object> exceptionHandler(){//也可以将返回结果改为错误页面展示
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("code", "500");//http响应状态码 500
		result.put("msg", "系统错误，请稍后重试....");//友好提示信息
		return result;
	}
}
