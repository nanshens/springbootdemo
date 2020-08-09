package com.ns.jpamultidatasource.repoone;

import com.ns.jpamultidatasource.entity.many2one.SalesOrder;
import com.ns.jpamultidatasource.repo.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nanshen
 */
@Repository
public interface SalesOrderRepo extends BaseRepository<SalesOrder, String> {

}
