package com.ns.springdatajpaquerydsl.repo.many2one;

import com.ns.springdatajpaquerydsl.entity.many2one.Customer;
import com.ns.springdatajpaquerydsl.repo.base.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ns
 * @create 2020-05-30
 */
@Repository
public interface CustomerRepo extends BaseRepository<Customer, String> {

	
}
