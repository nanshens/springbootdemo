package com.ns.mybatis;

import com.ns.mybatis.entity.Customer;
import com.ns.mybatis.entity.SalesOrder;
import com.ns.mybatis.mapper.CustomerMapper;
import com.ns.mybatis.mapper.SalesOrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisApplicationTests {

	@Autowired CustomerMapper customerMapper;
	@Autowired SalesOrderMapper salesOrderMapper;
	@Test
	void contextLoads() {
		Customer customer = new Customer();
		customer.setName("name");
		customer.setCode("code");


		int id = customerMapper.insert(customer);

		int id1 = customerMapper.update("646908fa-472d-4db1-8ea2-fd3c0df9c9c6", "name1");

		Customer c1 = customerMapper.select("646908fa-472d-4db1-8ea2-fd3c0df9c9c6");

		List<Customer> customers = customerMapper.selectAll();


		System.out.println();
	}

	@Test
	void contextLoads1() {
		SalesOrder salesOrder = salesOrderMapper.findById("1");
		Customer customer = customerMapper.findById("646908fa-472d-4db1-8ea2-fd3c0df9c9c6");

		System.out.println();
	}
}
