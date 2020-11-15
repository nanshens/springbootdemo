package com.ns.dubboprovider.serviceImpl;

import com.ns.dubbointerface.entity.Customer;
import com.ns.dubbointerface.service.ICustomerService;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @author ns
 * @create 2020-09-11
 */
@Service
public class CustomerServiceImpl implements ICustomerService {
    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public Customer getById(String id) {
        Customer customer = new Customer();
        customer.setName(id);
        return customer;
    }
}
