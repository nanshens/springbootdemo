package com.ns.shardingsphere.repo;


import com.ns.shardingsphere.entity.Item;
import org.springframework.stereotype.Repository;

/**
 * @author ns
 * @create 2020-05-30
 */
@Repository
public interface ItemRepo extends BaseRepository<Item, String> {

	
}
