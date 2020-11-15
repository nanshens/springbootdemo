package com.ns.dubboconsumer;

import com.ns.dubbointerface.entity.Customer;
import com.ns.dubbointerface.service.ICustomerService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DubboConsumerApplicationTests {
    @Reference
    ICustomerService customerService;
    @Test
    void contextLoads() {
        Customer customer = customerService.getById("1");
        System.out.println();
    }

}
