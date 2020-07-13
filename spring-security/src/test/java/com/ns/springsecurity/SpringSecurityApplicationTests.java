package com.ns.springsecurity;

import com.ns.springsecurity.entity.SecurityUser;
import com.ns.springsecurity.entity.SystemUser;
import com.ns.springsecurity.repo.SystemUserRepo;
import com.ns.springsecurity.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringSecurityApplicationTests {

	@Autowired private JwtUtil jwtUtil;
	@Autowired
	SystemUserRepo systemUserRepo;
	@Test
	void contextLoads() {
		SecurityUser securityUser = new SecurityUser();
		securityUser.setUsername("admin");
		securityUser.setPassword("123");
		String jwt = jwtUtil.generateToken(securityUser);
		SystemUser systemUser = new SystemUser();


		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		systemUser.setPassword(encoder.encode("123"));
		systemUser.setUsername("admin");
		systemUserRepo.save(systemUser);
		System.out.println(jwt);
	}

}
