package com.ns.jooq;

import com.ns.jooq.generate.tables.Customer;
import com.ns.jooq.generate.tables.SalesOrder;
import com.ns.jooq.generate.tables.records.CustomerRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JooqApplicationTests {
    @Autowired
    DSLContext dsl;

    Customer customer = Customer.CUSTOMER;
    SalesOrder salesOrder = SalesOrder.SALES_ORDER;

    @Test
    void contextLoads() {
        dsl.insertInto(customer)
                .columns(customer.ID, customer.NAME)
                .values("2", "2")
                .execute();

        CustomerRecord customerRecord = new CustomerRecord("3", "name3");
        dsl.insertInto(customer).set(customerRecord).execute();
    }

    @Test
    void contextLoads1() {
//        dsl.update(customer).set((Record) customer);
//        dsl.delete(customer).where(customer.ID.eq("1"));
        Result result = dsl.select().from(Customer.CUSTOMER).fetch();

        Result result1 = dsl.select(customer.ID, customer.NAME)
                .from(customer)
                .where(customer.ID.eq("1")).fetch();

        System.out.println();
    }


}
