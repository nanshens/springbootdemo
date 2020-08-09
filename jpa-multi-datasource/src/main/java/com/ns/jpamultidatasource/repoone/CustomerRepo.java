package com.ns.jpamultidatasource.repoone;


import com.ns.jpamultidatasource.entity.many2one.Customer;
import com.ns.jpamultidatasource.repo.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ns
 * @create 2020-05-30
 */
@Repository
public interface CustomerRepo extends BaseRepository<Customer, String> {

	
}
