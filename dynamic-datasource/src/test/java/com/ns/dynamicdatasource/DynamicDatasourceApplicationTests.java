package com.ns.dynamicdatasource;

import com.ns.dynamicdatasource.entity.many2one.Customer;
import com.ns.dynamicdatasource.service.CustomerMasterService;
import com.ns.dynamicdatasource.service.CustomerSlave1Service;
import com.ns.dynamicdatasource.service.CustomerSlave2Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DynamicDatasourceApplicationTests {

    @Autowired
    CustomerMasterService customerMasterService;

    @Autowired
    CustomerSlave1Service customerSlave1Service;

    @Autowired
    CustomerSlave2Service customerSlave2Service;

    @Test
    void contextLoads() {

        List<Customer> customers1 = customerMasterService.findAll();
        List<Customer> customers2 = customerSlave1Service.findAll();
        List<Customer> customers3 = customerSlave2Service.findAll();


        System.out.println();

    }

}
