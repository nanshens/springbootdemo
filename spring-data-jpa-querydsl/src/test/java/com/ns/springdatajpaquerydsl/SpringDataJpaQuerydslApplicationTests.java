package com.ns.springdatajpaquerydsl;

import com.ns.springdatajpaquerydsl.entity.many2one.Customer;
import com.ns.springdatajpaquerydsl.entity.many2one.QCustomer;
import com.ns.springdatajpaquerydsl.entity.many2one.QSalesOrder;
import com.ns.springdatajpaquerydsl.entity.many2one.SalesOrder;
import com.ns.springdatajpaquerydsl.repo.many2one.CustomerRepo;
import com.ns.springdatajpaquerydsl.repo.many2one.SalesOrderRepo;
import com.ns.springdatajpaquerydsl.vo.SalesOrderVO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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

		List<SalesOrderVO> dtoList1 = queryFactory
				.select(Projections.bean(SalesOrderVO.class, qSalesOrder.name, qSalesOrder.code, qCustomer.name.as("customerName")))
				.from(qSalesOrder)
				.leftJoin(QSalesOrder.salesOrder.customer, qCustomer)
				.fetch();

		List<String> distinctNameList = queryFactory.selectDistinct(qSalesOrder.code).from(qSalesOrder).fetch();

		SalesOrder firstMember = queryFactory.selectFrom(qSalesOrder).fetchFirst();

		SalesOrder anotherFirstMember = queryFactory.selectFrom(qSalesOrder).fetchOne();

		System.out.println();
	}

	@Test
	void query() {
//		List<SalesOrder> salesOrders = salesOrderRepo.findAll();

		QSalesOrder qSalesOrder = QSalesOrder.salesOrder;
		QCustomer qCustomer = QCustomer.customer;

		List<SalesOrder> salesOrders = queryFactory
				.selectFrom(qSalesOrder)
				.where(qSalesOrder.name.like('%'+"Jack"+'%')
//						.and(qSalesOrder..contains("厦门"))
						.and(qSalesOrder.name.eq("0013"))
						.and(qSalesOrder.age.between(20, 30)))
				.fetch();
	}


	@Test
	void query1() {
		QSalesOrder qSalesOrder = QSalesOrder.salesOrder;
		QCustomer qCustomer = QCustomer.customer;

		BooleanBuilder builder = new BooleanBuilder();

		builder.and(qSalesOrder.name.like('%'+"Jack"+'%'))
				.and(qSalesOrder.code.eq("0013"))
				.and(qSalesOrder.age.between(20, 30));

		List<SalesOrder> salesOrders = queryFactory.selectFrom(qSalesOrder).where(builder).fetch();
	}

	@Test
	void query2() {
		QSalesOrder qSalesOrder = QSalesOrder.salesOrder;
		QCustomer qCustomer = QCustomer.customer;

		List<SalesOrder> leftJoinList = queryFactory
				.selectFrom(qSalesOrder)
				.leftJoin(qSalesOrder.customer, qCustomer)
				.where(qCustomer.name.eq("1"))
				.fetch();


		List<SalesOrder> salesOrders = queryFactory
				.select(qSalesOrder)
				.from(qSalesOrder, qCustomer)
				.where(qSalesOrder.name.eq(qCustomer.name).and(qSalesOrder.name.eq("123")))
				.fetch();

	}

	@Test
	void query3() {
		QSalesOrder qSalesOrder = QSalesOrder.salesOrder;
		QCustomer qCustomer = QCustomer.customer;

		Iterable<SalesOrder> iterable = salesOrderRepo.findAll(qSalesOrder.name.eq("0013"));

		BooleanBuilder builder = new BooleanBuilder();

		builder.and(qSalesOrder.name.like('%'+"Jack"+'%'))
				.and(qSalesOrder.code.eq("0013"))
				.and(qSalesOrder.age.between(20, 30));

		OrderSpecifier<Integer> order = new OrderSpecifier<>(Order.DESC, qSalesOrder.age);

//		Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "age"));

//		Iterable<SalesOrder> iterable1 = salesOrderRepo.findAll(builder, sort);
		Iterable<SalesOrder> iterable2 = salesOrderRepo.findAll(builder, order);
	}

	@Test
	void query4() {
		QSalesOrder qSalesOrder = QSalesOrder.salesOrder;
		QCustomer qCustomer = QCustomer.customer;

		List<SalesOrder> subList = queryFactory
				.selectFrom(qSalesOrder)
				.where(qSalesOrder.name.eq(JPAExpressions.select(qSalesOrder.name).from(qSalesOrder)))
				.fetch();
	}

	@Test
	void query5() {
		QSalesOrder qSalesOrder = QSalesOrder.salesOrder;
		List<SalesOrder> orderList = queryFactory
				.selectFrom(qSalesOrder)
				.orderBy(qSalesOrder.name.asc())
				.fetch();
	}

	@Test
	void query6() {
		QSalesOrder qSalesOrder = QSalesOrder.salesOrder;
		//写法一
		JPAQuery<SalesOrder> query = queryFactory
				.selectFrom(qSalesOrder)
				.orderBy(qSalesOrder.age.asc());
		long total = query.fetchCount();//fetchCount的时候上面的orderBy不会被执行
		List<SalesOrder> list0= query.offset(2).limit(5).fetch();
		//写法二
		QueryResults<SalesOrder> results = queryFactory
				.selectFrom(qSalesOrder)
				.orderBy(qSalesOrder.age.asc())
				.offset(2).limit(5)
				.fetchResults();
		List<SalesOrder> list = results.getResults();
	}

	@Test
	void query7() {
		QSalesOrder qSalesOrder = QSalesOrder.salesOrder;
		QCustomer qCustomer = QCustomer.customer;

		List<SalesOrderVO> salesOrderVOS = queryFactory
				.select(
						qSalesOrder.id,
						qSalesOrder.name,
						qSalesOrder.code,
						qCustomer.name
				)
				.from(qSalesOrder, qCustomer)
				.where(qSalesOrder.name.eq(qCustomer.name))
				.fetch()
				.stream()
				.map(tuple -> {
					SalesOrderVO dto = new SalesOrderVO();
					dto.setCode(tuple.get(qSalesOrder.id));
					dto.setName(tuple.get(qSalesOrder.name));
					dto.setCustomerName(tuple.get(qCustomer.name));
					return dto;
				})
				.collect(Collectors.toList());
	}

	@Test
	void query8() {
		QSalesOrder qSalesOrder = QSalesOrder.salesOrder;

		List<SalesOrder> salesOrders = queryFactory
				.selectFrom(qSalesOrder)
				.where(qSalesOrder.age.eq(
						JPAExpressions
								.select(qSalesOrder.age.max())
								.from(qSalesOrder)
				))
				.fetch();
	}

	@Test
	void query9() {
		QSalesOrder qSalesOrder = QSalesOrder.salesOrder;

		long count = queryFactory
				.select(qSalesOrder.id.count())
				.from(qSalesOrder)
				.fetchOne();

		long sum = queryFactory
				.select(qSalesOrder.age.sum())
				.from(qSalesOrder)
				.fetchOne();

		List<SalesOrder> salesOrders = queryFactory
				.select(qSalesOrder)
				.from(qSalesOrder)
				.groupBy(qSalesOrder.age)
				.having(qSalesOrder.age.gt(22))
				.fetch();


	}



}
