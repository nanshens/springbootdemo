package com.ns.mybatismybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ns.mybatismybatisplus.entity.Customer;
import com.ns.mybatismybatisplus.mapper.CustomerMapper;
import com.ns.mybatismybatisplus.mapper.SalesOrderMapper;
import com.ns.mybatismybatisplus.service.CustomerServiceI;
import com.ns.mybatismybatisplus.service.SalesOrderServiceI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisMybatisplusApplicationTests {
	@Autowired
	CustomerMapper customerMapper;
	@Autowired
	SalesOrderMapper salesOrderMapper;
//	@Autowired
//	CustomerServiceI serviceI;
//	@Autowired
//	SalesOrderServiceI salesOrderServiceI;


	@Test
	void contextLoads() {
		List<Customer> customerList = customerMapper.selectList(null);
//		customerMapper.se
		customerMapper.insert(new Customer());
		Page<Customer> page = new Page<>();
		page.setSize(2);
		page.setPages(1);
//		customerMapper.selectPageVo(page);


		System.out.println();
	}

}
