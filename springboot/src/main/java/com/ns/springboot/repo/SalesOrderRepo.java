package com.ns.springboot.repo;

import com.ns.springboot.entity.Customer;
import com.ns.springboot.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ns
 * @create 2020-07-04
 */
@Repository
public interface SalesOrderRepo extends JpaRepository<SalesOrder, String> {

}
