package com.ns.shardingjdbcstarter.repo;


import com.ns.shardingjdbcstarter.entity.Item;
import org.springframework.stereotype.Repository;

/**
 * @author ns
 * @create 2020-05-30
 */
@Repository
public interface ItemRepo extends BaseRepository<Item, String> {

	
}
