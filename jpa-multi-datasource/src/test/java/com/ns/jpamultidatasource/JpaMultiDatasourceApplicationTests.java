package com.ns.jpamultidatasource;

import com.ns.jpamultidatasource.entity.many2one.Customer;
import com.ns.jpamultidatasource.repoone.CustomerRepo;
import com.ns.jpamultidatasource.repotwo.CustomerRepoTwo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JpaMultiDatasourceApplicationTests {

    @Autowired
    CustomerRepo customerRepoOne;

    @Autowired
    CustomerRepoTwo customerRepoOneTwo;

    @Test
    void contextLoads() {

        List<Customer> customerList1 = customerRepoOne.findAll();

        List<Customer> customerList2 = customerRepoOneTwo.findAll();

        System.out.println();
    }

}
