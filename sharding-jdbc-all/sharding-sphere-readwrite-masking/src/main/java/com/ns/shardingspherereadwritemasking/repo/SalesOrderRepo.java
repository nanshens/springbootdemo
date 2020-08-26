package com.ns.shardingspherereadwritemasking.repo;

import com.ns.shardingspherereadwritemasking.entity.SalesOrder;
import org.springframework.stereotype.Repository;

/**
 * @author nanshen
 */
@Repository
public interface SalesOrderRepo extends BaseRepository<SalesOrder, String> {

}
