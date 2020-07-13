package com.ns.springsecurity.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author ns
 * @create 2020-07-04
 */

public class CustomSecurityException extends AuthenticationException {
	public CustomSecurityException(String msg, Throwable t) {
		super(msg, t);
	}

	public CustomSecurityException(String msg) {
		super(msg);
	}
}
