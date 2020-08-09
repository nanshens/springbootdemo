package com.ns.jpamultidatasource.repotwo;


import com.ns.jpamultidatasource.entity.many2one.Customer;
import com.ns.jpamultidatasource.repo.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ns
 * @create 2020-05-30
 */
@Repository
public interface CustomerRepoTwo extends BaseRepository<Customer, String> {

	
}
