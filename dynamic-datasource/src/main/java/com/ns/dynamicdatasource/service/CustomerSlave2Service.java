package com.ns.dynamicdatasource.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ns.dynamicdatasource.entity.many2one.Customer;
import com.ns.dynamicdatasource.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ns
 * @create 2020-08-09
 */
@Service
@DS("slave_2")
public class CustomerSlave2Service {
    @Autowired
    CustomerRepo customerRepo;

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }
}
