package com.ns.springdatajpaquerydsl;

import com.ns.springdatajpaquerydsl.entity.many2one.Customer;
import com.ns.springdatajpaquerydsl.entity.many2one.PurchaseOrder;
import com.ns.springdatajpaquerydsl.entity.many2one.QCustomer;
import com.ns.springdatajpaquerydsl.entity.many2one.QPurchaseOrder;
import com.ns.springdatajpaquerydsl.entity.many2one.QSalesOrder;
import com.ns.springdatajpaquerydsl.entity.many2one.SalesOrder;
import com.ns.springdatajpaquerydsl.entity.many2one.Status;
import com.ns.springdatajpaquerydsl.repo.many2one.CustomerRepo;
import com.ns.springdatajpaquerydsl.repo.many2one.PurchaseOrderRepo;
import com.ns.springdatajpaquerydsl.repo.many2one.SalesOrderRepo;
import com.ns.springdatajpaquerydsl.vo.SalesOrderVO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
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
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 问题1:
 * pom引入jar和插件, 编写实体代码 用maven compile生成QEntity的对象,路径在 target/generated-source/java
 * 使用querydsl的两种方式
 * 1. queryFactory, 此方法功能强大,自定义能力强,适合更为复杂的场景.
 * 2. entity的repository 继承 QuerydslPredicateExecutor<T>. 使用简单, 不能执行一些复杂的查询.
 *
 */
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
	@Autowired private PurchaseOrderRepo purchaseOrderRepo;


	@Test
	void addCustomer() {
		Customer customer = new Customer();
		customer.setName("1");

		customerRepo.save(customer);
	}

	@Test
	void addSalesOrder() {
		List<Customer> customers = customerRepo.findAll();
		SalesOrder salesOrder = new SalesOrder();
		salesOrder.setCode("12");
		salesOrder.setName("23");
		salesOrder.setAge(12);
		salesOrder.setActive(true);
		salesOrder.setDate(LocalDate.now());
		salesOrder.setCustomer(customers.get(1));

		salesOrderRepo.save(salesOrder);
	}

	@Test
	void addPurchaseOrder() {
		List<SalesOrder> salesOrders = salesOrderRepo.findAll();

		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setSoId(salesOrders.get(2).getId());
		purchaseOrder.setCode("p3");
		purchaseOrder.setNumber(123);
		purchaseOrder.setDate(LocalDate.now());
		purchaseOrder.setActive(true);
		purchaseOrder.setStatus(Status.Closed);

		purchaseOrderRepo.save(purchaseOrder);

	}

	/**
	 * 问题2: 简单查询
	 */
	@Test
	void testQuery1() {
		QSalesOrder qSalesOrder = QSalesOrder.salesOrder;
		QCustomer qCustomer = QCustomer.customer;
		QPurchaseOrder qPurchaseOrder = QPurchaseOrder.purchaseOrder;

		/*
		 * 查询一个: 如果结果多于一个会报错
		 * */
		System.out.println("----------查询一个-------------");
		SalesOrder so1 = queryFactory.selectFrom(qSalesOrder).where(qSalesOrder.code.eq("so1")).fetchFirst();
		SalesOrder so2 = queryFactory.selectFrom(qSalesOrder).where(qSalesOrder.code.eq("so1")).fetchOne();
		System.out.println("----------查询一个-------------");

		/*
		 * 查询所有
		 * */
		System.out.println("----------查询所有-------------");
		List<SalesOrder> sol1 = queryFactory.selectFrom(qSalesOrder).fetch();
		List<SalesOrder> sol2 = queryFactory.select(qSalesOrder).from(qSalesOrder).fetch();
		System.out.println("----------查询所有-------------");

		/*
		 * 查询某一列带条件, 去重查询
		 * */
		System.out.println("----------查询查询某一列-------------");
		List<String> soCode = queryFactory.select(qSalesOrder.code).from(qSalesOrder).fetch();
		List<String> distinctSoCode = queryFactory.selectDistinct(qSalesOrder.code).from(qSalesOrder).fetch();
		List<String> soCode1 = queryFactory.select(qSalesOrder.code).from(qSalesOrder)
				.where(qSalesOrder.date.before(LocalDate.now())).fetch();
		System.out.println("----------查询查询某一列-------------");

		/*
		 *	查询某几列
		 * */
		System.out.println("----------查询某几列-------------");
		List<Tuple> tuples = queryFactory.select(qSalesOrder.code, qSalesOrder.name, qSalesOrder.age, qCustomer.name).from(qSalesOrder).fetch();
		System.out.println("----------查询某几列-------------");

		/*
		 *	查询结果映射dto
		 *	Projections.constructor 按照构造器顺序映射, 可以有重名,例如salesorder name和customer name
		 * 	Projections.bean 按照属性名映射, 顺序可以不同, 但名必须有相同的
		 * */
		System.out.println("----------查询结果映射dto-------------");
		List<SalesOrderVO> dtoList = queryFactory
				.select(Projections.constructor(SalesOrderVO.class, qSalesOrder.code, qSalesOrder.name, qCustomer.name))
				.from(qSalesOrder)
				.fetch();

		List<SalesOrderVO> dtoList1 = queryFactory
				.select(Projections.bean(SalesOrderVO.class, qSalesOrder.name, qSalesOrder.code, qCustomer.name.as("customerName")))
				.from(qSalesOrder)
				.fetch();

		List<SalesOrderVO> dtoList2 = queryFactory
				.select(qSalesOrder.id, qSalesOrder.name, qSalesOrder.code, qCustomer.name)
				.from(qSalesOrder).fetch()
				.stream()
				.map(tuple -> {
					SalesOrderVO dto = new SalesOrderVO();
					dto.setCode(tuple.get(qSalesOrder.id));
					dto.setName(tuple.get(qSalesOrder.name));
					dto.setCustomerName(tuple.get(qCustomer.name));
					return dto;
				})
				.collect(Collectors.toList());

		System.out.println("----------查询结果映射dto-------------");
	}

	/**
	 * 复杂条件
	 */
	@Test
	void testQuery2() {
		QSalesOrder qSalesOrder = QSalesOrder.salesOrder;
		QCustomer qCustomer = QCustomer.customer;
		QPurchaseOrder qPurchaseOrder = QPurchaseOrder.purchaseOrder;

		/*
		 * 复杂条件 and and and
		 * */
		System.out.println("----------复杂条件-------------");
		List<SalesOrder> salesOrders1 = queryFactory
				.selectFrom(qSalesOrder)
				.where(qSalesOrder.code.eq("so1")
						.and(qSalesOrder.age.between(12, 20))
						.and(qSalesOrder.name.like("%n%"))
						.and(qSalesOrder.status.in(Status.Closed, Status.Completed)))
				.fetch();

		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qSalesOrder.code.eq("so1"))
				.and(qSalesOrder.age.between(12, 20))
				.and(qSalesOrder.name.like("%n%"))
				.and(qSalesOrder.status.in(Status.Closed, Status.Completed));
		List<SalesOrder> salesOrders2 = queryFactory.selectFrom(qSalesOrder).where(builder).fetch();

		BooleanBuilder builder3 = new BooleanBuilder();
		builder3.and(qSalesOrder.code.eq("so1"))
				.and(qSalesOrder.age.between(12, 20).or(qSalesOrder.name.like("%n%")))
				.and(qSalesOrder.status.in(Status.Closed, Status.Completed));
		List<SalesOrder> salesOrders3 = queryFactory.selectFrom(qSalesOrder).where(builder3).fetch();
		System.out.println("----------复杂条件-------------");

	}

	/**
	 * 连表查询, 子查询
	 */
	@Test
	void testQuery3() {
		QSalesOrder qSalesOrder = QSalesOrder.salesOrder;
		QCustomer qCustomer = QCustomer.customer;
		QPurchaseOrder qPurchaseOrder = QPurchaseOrder.purchaseOrder;

		/*
		 * 查询不会连表, 关联的对象在加载时会请求(n+1问题)
		 * */
		System.out.println("----------普通查询-------------");
		List<SalesOrder> sol1 = queryFactory.selectFrom(qSalesOrder).fetch();
		System.out.println("----------普通查询-------------");

		/*
		 * 有实体关联的查询
		 * 连表查询: left join和 cross join. right join, inner join 等也是类似写法.使用时注意各种查询的区别,以免影响效率.
		 *
		 * 没有实体关联的查询 只能通过cross join方式. where中写好对应关系
		 *
		 * */
		System.out.println("----------连表查询-------------");
		List<SalesOrder> leftJoinList = queryFactory
				.selectFrom(qSalesOrder)
				.leftJoin(qSalesOrder.customer, qCustomer)
				.fetch();

		List<SalesOrder> salesOrders = queryFactory
				.select(qSalesOrder)
				.from(qSalesOrder, qCustomer)
				.fetch();

		List<PurchaseOrder> purchaseorders = queryFactory
				.select(qPurchaseOrder)
				.from(qPurchaseOrder, qSalesOrder)
				.where(qPurchaseOrder.soId.eq(qSalesOrder.id))
				.fetch();

		System.out.println("----------连表查询-------------");

		/*
		 * 子查询
		 * */
		System.out.println("----------子查询-------------");
		List<PurchaseOrder> purchaseorders1 = queryFactory
				.selectFrom(qPurchaseOrder)
				.where(qPurchaseOrder.soId.in(JPAExpressions.select(qSalesOrder.id).from(qSalesOrder)))
				.fetch();
		System.out.println("----------子查询-------------");
	}


	/**
	 * 排序, 分页
	 */
	@Test
	void testQuery4() {
		QSalesOrder qSalesOrder = QSalesOrder.salesOrder;
		QCustomer qCustomer = QCustomer.customer;
		QPurchaseOrder qPurchaseOrder = QPurchaseOrder.purchaseOrder;

		/*
		 *
		 * */
		System.out.println("----------普通查询-------------");
		List<SalesOrder> sol1 = queryFactory.selectFrom(qSalesOrder).fetch();
		System.out.println("----------普通查询-------------");

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

		List<SalesOrder> orderList = queryFactory
				.selectFrom(qSalesOrder)
				.orderBy(qSalesOrder.name.asc())
				.fetch();
	}

	/**
	 * 分组, 聚合
	 */
	@Test
	void testQuery5() {
		QSalesOrder qSalesOrder = QSalesOrder.salesOrder;
		QCustomer qCustomer = QCustomer.customer;
		QPurchaseOrder qPurchaseOrder = QPurchaseOrder.purchaseOrder;

		/*
		 *
		 * */
		System.out.println("----------普通查询-------------");
		List<SalesOrder> sol1 = queryFactory.selectFrom(qSalesOrder).fetch();
		System.out.println("----------普通查询-------------");

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

	/**
	 * QuerydslPredicateExecutor的使用
	 */
	@Test
	void testQuery6() {
		QSalesOrder qSalesOrder = QSalesOrder.salesOrder;
		QCustomer qCustomer = QCustomer.customer;
		QPurchaseOrder qPurchaseOrder = QPurchaseOrder.purchaseOrder;

		/*
		 *
		 * */
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
}
