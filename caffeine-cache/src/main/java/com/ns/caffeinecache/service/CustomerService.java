package com.ns.caffeinecache.service;

import com.ns.caffeinecache.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ns
 * @create 2020-09-20
 */
@Service
@CacheConfig(cacheNames = "customer")
public class CustomerService {
    @Autowired private CacheManager caffeineCacheManager;
    private Map<String, Customer> customers = new HashMap<>();

    {
        customers.put("1", new Customer("1", "customer1", "c1", "11111"));
        customers.put("2", new Customer("2", "customer2", "c2", "22222"));
        customers.put("3", new Customer("3", "customer3", "c3", "33333"));
        customers.put("4", new Customer("4", "customer4", "c4", "44444"));
        customers.put("5", new Customer("5", "customer5", "c5", "55555"));
    }

    public void initCache() {
        Cache cache = caffeineCacheManager.getCache("customer");
        assert cache != null;
        customers.forEach(cache::put);
    }

    @CachePut(value = "customer" ,key = "#customer.id")
    public Customer create(Customer customer) {
        customers.put(customer.getId(), customer);
        return customer;
    }

    @Cacheable(key = "#id")
    public Customer getById(String id) {
        System.out.println("customer by database");
        return customers.get(id);
    }

    public Map<String, Customer> getAll() {
        System.out.println("customer getAll by database");
        return customers;
    }

    @CacheEvict(key = "#id")
    public void deleteById(String id) {
        customers.remove(id);
    }
}
