package com.ns.shardingsphere.repo;


import com.ns.shardingsphere.entity.Customer;
import org.springframework.stereotype.Repository;

/**
 * @author ns
 * @create 2020-05-30
 */
@Repository
public interface CustomerRepo extends BaseRepository<Customer, String> {

	
}
