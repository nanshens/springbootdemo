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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

	/**
	 * 问题9
	 *
	 */
	@Test
	void select1() {
		List<SalesOrderVO> salesOrders = salesOrderRepo.findAllByJpql();
		List<Object[]> salesOrders1 = salesOrderRepo.findAllByNativeSql();
		List<SalesOrderVO> list1 = EntityMapper.mapping(salesOrders1, SalesOrderVO.class, 0);

		List<Object[]> salesOrders2 = salesOrderRepo.findAllByNativeSql1();
		List<SalesOrderVO> list2 = EntityMapper.mapping(salesOrders2, SalesOrderVO.class, "code", "customerName");
		System.out.println();
	}

	/** 问题7
	 * jpa 用Specification来支持复杂查询, 但使用和编码不太友好, 这里进行简单演示
	 * 对于经常使用Specification的项目,强烈建议对其封装,原生写法是真的难用
	 * (本作者进行了简单封装,现还在维护中,也上传了中央仓库, 项目地址为: https://github.com/nanshens/jpa-starter)
	 * 或者使用其他开源项目增强查询(例如querydsl, 在另一个spring-data-jpa-querydsl中详细介绍及使用querydsl)
	 * 通过重写topredicate方法,实现复杂查询.
	 */
	@Test
	void selectSpecification() {
		Pageable pageable = PageRequest.of(0,1, Sort.Direction.DESC, "code");
		Specification<SalesOrder> spec = (Specification) (root, query, cb) -> {
			Path<String> code = root.get("code");
			Path<String> custId = root.get("customer").get("id");

			Predicate p1 = cb.equal(code, "s1");
			Predicate p2 = cb.equal(custId, "402886c672660e790172660e7f160000");
			return cb.and(p1, p2);
		};
		List<SalesOrder> salesOrders = salesOrderRepo.findAll(spec);
		Page<SalesOrder> salesOrderPage = salesOrderRepo.findAll(spec, pageable);
	}
}
