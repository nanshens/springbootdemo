package com.ns.springboot.service;

import com.ns.springboot.entity.SalesOrder;
import com.ns.springboot.repo.SalesOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ns
 * @create 2020-07-04
 */
@Service
public class SalesOrderService {
    @Autowired private SalesOrderRepo salesOrderRepo;

    public void save() {
        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setCode("C1");
        salesOrder.setVersion(1);

        salesOrderRepo.save(salesOrder);
    }
}
