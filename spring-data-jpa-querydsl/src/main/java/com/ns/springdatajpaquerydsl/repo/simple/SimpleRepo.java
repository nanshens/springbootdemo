package com.ns.springdatajpaquerydsl.repo.simple;

import com.ns.springdatajpaquerydsl.entity.simple.Simple;
import com.ns.springdatajpaquerydsl.repo.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ns
 * @create 2020-05-30
 *
 * 1.对于简单的查询, 可以使用jpa提供的简单方法
 * countby
 * existby
 * findby
 * findFirstby
 *
 * by后面语法详情进官网了解 https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
 * 语法很简单
 *
 * 注意如果find获取尽量用list接受, 除非是能确定唯一性,不然查出多条数据会有异常
 *
 * 2.可以手动书写sql语句
 *
 */
@Repository
public interface SimpleRepo extends BaseRepository<Simple, String> {
	int countByActiveIsTrue();
	List<Simple> findByActiveIsTrue();


	/**
	 * 对于自定义sql
	 * 可以指定使用原生sql还是jpql,具体使用就不在这展开了.
	 */
	@Query(value = "select * from simple", nativeQuery = true)
	List<Simple> findByNativeSql();

}
