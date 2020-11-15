package com.ns.dubbointerface.service;

import com.ns.dubbointerface.entity.SalesOrder;

/**
 * @author ns
 * @create 2020-09-11
 */

public interface ISalesOrderService {
    SalesOrder create(String code);
}
