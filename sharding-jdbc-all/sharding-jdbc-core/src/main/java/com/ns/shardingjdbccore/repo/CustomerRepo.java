package com.ns.shardingjdbccore.repo;


import com.ns.shardingjdbccore.entity.Customer;
import org.springframework.stereotype.Repository;

/**
 * @author ns
 * @create 2020-05-30
 */
@Repository
public interface CustomerRepo extends BaseRepository<Customer, String> {

	
}
