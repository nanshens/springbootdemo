package com.ns.dynamicdatasource.repo;


import com.ns.dynamicdatasource.entity.many2one.Customer;
import org.springframework.stereotype.Repository;

/**
 * @author ns
 * @create 2020-05-30
 */
@Repository
public interface CustomerRepo extends BaseRepository<Customer, String> {

	
}
