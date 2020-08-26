package com.ns.shardingjdbcstarter.repo;


import com.ns.shardingjdbcstarter.entity.SalesOrderLine;
import org.springframework.stereotype.Repository;

/**
 * @author ns
 * @create 2020-05-30
 */
@Repository
public interface SalesOrderLineRepo extends BaseRepository<SalesOrderLine, String> {

	
}
