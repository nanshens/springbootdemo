package com.ns.caffeinecache.service;

import com.ns.caffeinecache.entity.Customer;
import com.ns.caffeinecache.entity.SalesOrder;
import com.ns.caffeinecache.entity.SysSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ns
 * @create 2020-09-20
 */
@Service
@CacheConfig(cacheNames = "salesorder")
public class SalesOrderService {
    private Map<String, SalesOrder> salesOrders = new HashMap<>();
    @Autowired private CacheManager caffeineCacheManager;

    @Autowired private CustomerService customerService;
    @Autowired private SysSettingService sysSettingService;

    {
        salesOrders.put("1", new SalesOrder("1", "so1", "so1"));
        salesOrders.put("2", new SalesOrder("2", "so2", "so2"));
        salesOrders.put("3", new SalesOrder("3", "so3", "so3"));
        salesOrders.put("4", new SalesOrder("4", "so4", "so4"));
        salesOrders.put("5", new SalesOrder("5", "so5", "so5"));
    }

//    @Cacheable(value = "salesorder" ,key = "targetClass + methodName + #p0")
//    public Map<String, SalesOrder> initCache() {
//        return salesOrders;
//    }

    public void initCache() {
        Cache cache = caffeineCacheManager.getCache("salesorder");
        assert cache != null;
        salesOrders.forEach(cache::put);
    }

    public Map<String, SalesOrder> getAll() {
        System.out.println("SalesOrder getAll by database");
        return salesOrders;
    }

    @CachePut(key = "#salesOrder.id")
    public SalesOrder create(SalesOrder salesOrder) {
        salesOrders.put(salesOrder.getId(), salesOrder);
        return salesOrder;
    }

    public SalesOrder getById(String id) {
        Customer customer = customerService.getById("1");
        SysSetting sysSetting = sysSettingService.getByName("1");
        return salesOrders.get(id);
    }
}
