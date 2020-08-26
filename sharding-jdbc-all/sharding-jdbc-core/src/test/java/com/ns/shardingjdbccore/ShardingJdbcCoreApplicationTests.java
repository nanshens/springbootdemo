package com.ns.shardingjdbccore;

import com.ns.shardingjdbccore.entity.Customer;
import com.ns.shardingjdbccore.entity.Item;
import com.ns.shardingjdbccore.entity.SalesOrder;
import com.ns.shardingjdbccore.entity.SalesOrderLine;
import com.ns.shardingjdbccore.repo.CustomerRepo;
import com.ns.shardingjdbccore.repo.ItemRepo;
import com.ns.shardingjdbccore.repo.SalesOrderLineRepo;
import com.ns.shardingjdbccore.repo.SalesOrderRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class ShardingJdbcCoreApplicationTests {


    @Autowired
    private SalesOrderRepo salesOrderRepo;
    @Autowired private SalesOrderLineRepo salesOrderLineRepo;
    @Autowired private CustomerRepo customerRepo;
    @Autowired private ItemRepo itemRepo;

    @Test
    void createCustomer() {
        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer();
            customer.setCode("C-" + i);
            customerRepo.save(customer);
        }
    }

    @Test
    void createItem() {
        for (int i = 0; i < 10; i++) {
            Item item = new Item();
            item.setName("Item-" + i);
            itemRepo.save(item);
        }
    }

    @Test
    void createSO() {
//        List<SalesOrder> salesOrders = salesOrderRepo.findAll();
        List<Customer> customers = customerRepo.findAll();

        for (int i = 0; i < 10; i++) {
            SalesOrder salesOrder = new SalesOrder();
            salesOrder.setCustomer(customers.get(i));
            salesOrder.setCode("SalesOrder-" + i);
            salesOrderRepo.save(salesOrder);
        }
    }

    @Test
    void createSOL() {
        List<SalesOrder> salesOrders = salesOrderRepo.findAll();
        List<Item> items = itemRepo.findAll();

        for (int i = 0; i < 10; i++) {
            SalesOrderLine salesOrderLine = new SalesOrderLine();
            salesOrderLine.setSalesOrder(salesOrders.get(i));
            salesOrderLine.setCost(BigDecimal.valueOf(i));
            salesOrderLine.setItem(items.get(i));
            salesOrderLineRepo.save(salesOrderLine);
        }

    }
}
