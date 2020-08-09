package com.ns.jpamultidatasource.repotwo;

import com.ns.jpamultidatasource.entity.many2one.SalesOrder;
import com.ns.jpamultidatasource.repo.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nanshen
 */
@Repository
public interface SalesOrderRepoTwo extends BaseRepository<SalesOrder, String> {

}
