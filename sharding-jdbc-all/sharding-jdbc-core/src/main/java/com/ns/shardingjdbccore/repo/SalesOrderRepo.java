package com.ns.shardingjdbccore.repo;

import com.ns.shardingjdbccore.entity.SalesOrder;
import org.springframework.stereotype.Repository;

/**
 * @author nanshen
 */
@Repository
public interface SalesOrderRepo extends BaseRepository<SalesOrder, String> {

}
