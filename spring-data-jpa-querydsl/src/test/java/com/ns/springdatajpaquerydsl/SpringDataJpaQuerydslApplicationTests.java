package com.ns.springdatajpaquerydsl;

import com.ns.springdatajpaquerydsl.entity.many2one.Customer;
import com.ns.springdatajpaquerydsl.entity.many2one.QCustomer;
import com.ns.springdatajpaquerydsl.entity.many2one.QSalesOrder;
import com.ns.springdatajpaquerydsl.entity.many2one.SalesOrder;
import com.ns.springdatajpaquerydsl.repo.many2one.CustomerRepo;
import com.ns.springdatajpaquerydsl.repo.many2one.SalesOrderRepo;
import com.ns.springdatajpaquerydsl.vo.SalesOrderVO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional(rollbackOn = Exception.class)
@Rollback(false)
@ComponentScan(basePackages = {"com.ns.springdatajpaquerydsl"})
class SpringDataJpaQuerydslApplicationTests {

	private JPAQueryFactory queryFactory;
	@Bean
	@Autowired
	public JPAQueryFactory jpaQuery(EntityManager entityManager) {
		queryFactory = new JPAQueryFactory(entityManager);
		return queryFactory;
	}

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
		salesOrder.setCustomer(customers.get(0));
		salesOrderRepo.save(salesOrder);
	}


	@Test
	void contextLoads() {
//		List<SalesOrder> salesOrders = salesOrderRepo.findAll();

		QSalesOrder qSalesOrder = QSalesOrder.salesOrder;
		QCustomer qCustomer = QCustomer.customer;
		List<SalesOrder> salesOrderList = queryFactory.selectFrom(qSalesOrder).fetch();

		List<String> nameList = queryFactory.select(qSalesOrder.name).from(qSalesOrder).fetch();
		List<SalesOrderVO> dtoList = queryFactory
				.select(Projections.constructor(SalesOrderVO.class, qSalesOrder.name, qSalesOrder.code, qCustomer.name))
				.from(qSalesOrder)
				.leftJoin(QSalesOrder.salesOrder.customer, qCustomer)
				.fetch();

		System.out.println();
	}



}
