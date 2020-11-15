package com.ns.springboot.repo;

import com.ns.springboot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ns
 * @create 2020-07-04
 */
@Repository
public interface CustomerRepo extends JpaRepository<Customer, String> {

}
