package com.ns.springboot;

import com.ns.springboot.interfaces.Pay;
import com.ns.springboot.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootApplicationTests {

	@Autowired private CustomerService customerService;

	@Autowired
	private Map<String, Pay> pays;

	@Test
	void contextLoads() {
		customerService.save();
	}

}
