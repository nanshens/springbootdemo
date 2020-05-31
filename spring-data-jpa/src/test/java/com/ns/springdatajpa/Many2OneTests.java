package com.ns.springdatajpa;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.ns.springdatajpa.entity.many2many.Course;
import com.ns.springdatajpa.entity.many2many.Student;
import com.ns.springdatajpa.entity.many2one.Customer;
import com.ns.springdatajpa.entity.many2one.SalesOrder;
import com.ns.springdatajpa.repo.many2many.CourseRepo;
import com.ns.springdatajpa.repo.many2many.StudentRepo;
import com.ns.springdatajpa.repo.many2one.CustomerRepo;
import com.ns.springdatajpa.repo.many2one.SalesOrderRepo;
import com.ns.springdatajpa.vo.SalesOrderVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional(rollbackOn = Exception.class)
@Rollback(false)
class Many2OneTests {
	@Autowired private CustomerRepo customerRepo;
	@Autowired private SalesOrderRepo salesOrderRepo;


	@Test
	void addCustomer() {
		Customer customer = new Customer();
		customer.setName("4");

		customerRepo.save(customer);
	}

	@Test
	void addSalesOrder() {
		List<Customer> customers = customerRepo.findAll();
		SalesOrder salesOrder = new SalesOrder();
		salesOrder.setCode("s8");
		salesOrder.setCustomer(customers.get(2));
		salesOrderRepo.save(salesOrder);
	}


	@Test
	void select() {
//		customerRepo.findAll();
//		List<SalesOrder> salesOrders = salesOrderRepo.findAllBySql();
		Pageable pageable = PageRequest.of(0, 1, Sort.Direction.ASC, "id");
		List<SalesOrder> salesOrders = salesOrderRepo.findAllByNativeSqlByCode("s1", pageable);
		System.out.println();
	}

	@Test
	void select1() {
		List<SalesOrderVO> salesOrders = salesOrderRepo.findAllByJpql();
		List<Object[]> salesOrders1 = salesOrderRepo.findAllByNativeSql();
		List<SalesOrderVO> list1 = EntityMapper.mapping(salesOrders1, SalesOrderVO.class, 0);

		List<Object[]> salesOrders2 = salesOrderRepo.findAllByNativeSql1();
		List<SalesOrderVO> list2 = EntityMapper.mapping(salesOrders2, SalesOrderVO.class, "code", "customerName");
		System.out.println();
	}
}
