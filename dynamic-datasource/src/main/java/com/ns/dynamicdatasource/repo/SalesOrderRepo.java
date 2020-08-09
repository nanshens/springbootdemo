package com.ns.dynamicdatasource.repo;

import com.ns.dynamicdatasource.entity.many2one.SalesOrder;
import org.springframework.stereotype.Repository;

/**
 * @author nanshen
 */
@Repository
public interface SalesOrderRepo extends BaseRepository<SalesOrder, String> {

}
