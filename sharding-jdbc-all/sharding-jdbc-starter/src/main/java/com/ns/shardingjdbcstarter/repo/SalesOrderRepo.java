package com.ns.shardingjdbcstarter.repo;

import com.ns.shardingjdbcstarter.entity.SalesOrder;
import org.springframework.stereotype.Repository;

/**
 * @author nanshen
 */
@Repository
public interface SalesOrderRepo extends BaseRepository<SalesOrder, String> {

}
