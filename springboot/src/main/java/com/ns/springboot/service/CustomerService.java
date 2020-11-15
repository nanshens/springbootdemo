package com.ns.springboot.service;

import com.ns.springboot.entity.Customer;
import com.ns.springboot.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;

/**
 * @author ns
 * @create 2020-07-04
 *
 * 1. Transactional 方法调用 无Transactional 方法
 * 2.Transactional-> 无Transactional->Transactional
 * 3.Transactional -> 无Transactional-> 其他Transactional
 * 4. 无Transactional -> Transactional-> 其他Transactional
 * 5. 测试手动回滚位置
 */
@Service
public class CustomerService {
    @Autowired private CustomerRepo customerRepo;
    @Autowired private SalesOrderService salesOrderService;

    @Transactional
    public void save() {
        Customer customer = new Customer();
        customer.setCode("C1");
        customer.setVersion(1);
        customerRepo.save(customer);
        save1();
    }


    public void save1() {
        Customer customer = new Customer();
        customer.setCode("C2");
        customer.setVersion(1);
        customerRepo.save(customer);
        salesOrderService.save();
        save2();
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
    @Transactional
    public void save2() {
        Customer customer = new Customer();
        customer.setCode("C3");
        customer.setVersion(1);
        customerRepo.save(customer);
    }
}
