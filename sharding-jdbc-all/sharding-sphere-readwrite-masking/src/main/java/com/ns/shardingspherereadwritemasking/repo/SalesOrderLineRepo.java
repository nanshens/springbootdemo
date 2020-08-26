package com.ns.shardingspherereadwritemasking.repo;


import com.ns.shardingspherereadwritemasking.entity.SalesOrderLine;
import org.springframework.stereotype.Repository;

/**
 * @author ns
 * @create 2020-05-30
 */
@Repository
public interface SalesOrderLineRepo extends BaseRepository<SalesOrderLine, String> {

	
}
