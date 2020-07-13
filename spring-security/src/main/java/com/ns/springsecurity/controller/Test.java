package com.ns.springsecurity.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ns
 * @create 2020-06-24
 */
@RestController
public class Test {
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

//	@PostMapping("/login")
//	public String login() {
//		return "login";
//	}

	@GetMapping("/user")
	public String user() {
		SecurityContextHolder.getContext().getAuthentication();
		return "user";
	}
}
