package com.ns.caffeinecache.config;

import com.ns.caffeinecache.service.CustomerService;
import com.ns.caffeinecache.service.SalesOrderService;
import com.ns.caffeinecache.service.SysSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author ns
 * @create 2020-09-21
 */
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Autowired private CustomerService customerService;
    @Autowired private SysSettingService sysSettingService;
    @Autowired private SalesOrderService salesOrderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        customerService.initCache();
        sysSettingService.initCache();
        salesOrderService.initCache();
    }
}
