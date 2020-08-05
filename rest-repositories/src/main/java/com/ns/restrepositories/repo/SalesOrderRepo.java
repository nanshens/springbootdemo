package com.ns.restrepositories.repo;

import com.ns.restrepositories.entity.SalesOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nanshen
 */
@Repository
public interface SalesOrderRepo extends BaseRepository<SalesOrder, String> {

}
