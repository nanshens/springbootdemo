package com.ns.shiro.handler;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ns
 * @create 2020-07-12
 */
@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler
	@ResponseBody
	public String errorHandler(AuthorizationException e) {
//		log.error("没有通过权限验证！", e);
		return "没有通过权限验证！";
	}
}
