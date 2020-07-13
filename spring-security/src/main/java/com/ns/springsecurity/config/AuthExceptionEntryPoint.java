package com.ns.springsecurity.config;

import com.ns.springsecurity.utils.HttpUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * @author ns
 */
@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint, Serializable {

	@Override
	public void commence(HttpServletRequest request,
						 HttpServletResponse response,
						 AuthenticationException authException) throws IOException {
		String message = request.getRequestURI() + " 认证失败：" + authException.getMessage();
		System.out.println(message);
		HttpUtil.sendResult(response, HttpStatus.FORBIDDEN.value(), message);
	}
}
