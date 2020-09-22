package com.ns.caffeinecache;

import com.github.benmanes.caffeine.cache.Cache;
import com.ns.caffeinecache.entity.Customer;
import com.ns.caffeinecache.entity.SysSetting;
import com.ns.caffeinecache.service.CustomerService;
import com.ns.caffeinecache.service.SalesOrderService;
import com.ns.caffeinecache.service.SysSettingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

@SpringBootTest
class CaffeineCacheApplicationTests {

    @Autowired private CustomerService customerService;
    @Autowired private SysSettingService sysSettingService;
    @Autowired private SalesOrderService salesOrderService;

    @Autowired private CacheManager caffeineCacheManager;
    @Autowired private Cache<String, SysSetting> sysSettingCache;

    @Test
    void contextLoads() {
        SysSetting sysSetting = sysSettingService.getByName("1");
        customerService.create(new Customer("6", "customer6", "c6", "66666"));
        customerService.getById("5");
        salesOrderService.getById("1");
        customerService.getAll();
        customerService.deleteById("1");
        System.out.println();
    }

}
