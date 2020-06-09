package com.ns.springdatajpaquerydsl.repo.many2one;

import com.ns.springdatajpaquerydsl.entity.many2one.PurchaseOrder;
import com.ns.springdatajpaquerydsl.entity.many2one.SalesOrder;
import com.ns.springdatajpaquerydsl.repo.base.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nanshen
 */
@Repository
public interface PurchaseOrderRepo extends BaseRepository<PurchaseOrder, String> {

}
