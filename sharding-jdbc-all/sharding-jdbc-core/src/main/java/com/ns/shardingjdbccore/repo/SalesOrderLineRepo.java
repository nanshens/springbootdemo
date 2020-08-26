package com.ns.shardingjdbccore.repo;


import com.ns.shardingjdbccore.entity.SalesOrderLine;
import org.springframework.stereotype.Repository;

/**
 * @author ns
 * @create 2020-05-30
 */
@Repository
public interface SalesOrderLineRepo extends BaseRepository<SalesOrderLine, String> {

	
}
