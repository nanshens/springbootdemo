package com.ns.springdatajpa.repo.many2one;

import com.ns.springdatajpa.entity.many2many.Student;
import com.ns.springdatajpa.entity.many2one.SalesOrder;
import com.ns.springdatajpa.repo.base.BaseRepository;
import com.ns.springdatajpa.vo.SalesOrderVO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nanshen
 */
@Repository
public interface SalesOrderRepo extends BaseRepository<SalesOrder, String> {

	@Override
	@EntityGraph(value="SalesOrder.find")
	List<SalesOrder> findAll();


	/**
	 * @return EntityGraph 不能使用在原生sql上
	 */
//	@EntityGraph(value="SalesOrder.find")
//	@Query(value="select * from salesorder", nativeQuery=true)
//	List<SalesOrder> findAllByNativeSql();

	@EntityGraph(value="SalesOrder.find")
	@Query(value="from SalesOrder")
	List<SalesOrder> findAllBySql();

	@Query(value="select * from sales_order where code=?1 order by ?#{#pageable}", nativeQuery=true)
	List<SalesOrder> findAllByNativeSqlByCode(String code, Pageable pageable);


	/**
	 * 自定义sql查询结果映射自定义实体
	 * 使用jpql语法
	 *
	 * @return
	 */
	@Query(value="select new com.ns.springdatajpa.vo.SalesOrderVO(so.code, so.name, c.name) from SalesOrder so, Customer c where c.id = so.customer.id")
	List<SalesOrderVO> findAllByJpql();


	/**
	 * 自定义sql查询结果映射自定义实体
	 * 使用原生sql语法
	 * 返回值用List<Object[]>接受, 我们需要自己实现一个通用转换器, 把Object[]转换成vo, 这样可以复用
	 * 本demo做了简单实现,如有复杂情况请自己实现
	 * @return
	 */
	@Query(value="select so.code as code, so.name as name, c.name as customerName from sales_order so left join customer c on c.id=so.customer_id ", nativeQuery=true)
	List<Object[]> findAllByNativeSql();

	@Query(value="select so.code as code, c.name as customerName from sales_order so left join customer c on c.id=so.customer_id ", nativeQuery=true)
	List<Object[]> findAllByNativeSql1();

}
