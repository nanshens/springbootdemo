package com.ns.dubbointerface.service;

import com.ns.dubbointerface.entity.Customer;

import java.util.List;

/**
 * @author ns
 * @create 2020-09-11
 */

public interface ICustomerService {

    List<Customer> getAll();
    Customer getById(String id);
}
