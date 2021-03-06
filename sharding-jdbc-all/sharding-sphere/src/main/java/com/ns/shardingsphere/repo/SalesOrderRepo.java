package com.ns.shardingsphere.repo;

import com.ns.shardingsphere.entity.SalesOrder;
import org.springframework.stereotype.Repository;

/**
 * @author nanshen
 */
@Repository
public interface SalesOrderRepo extends BaseRepository<SalesOrder, String> {

}
