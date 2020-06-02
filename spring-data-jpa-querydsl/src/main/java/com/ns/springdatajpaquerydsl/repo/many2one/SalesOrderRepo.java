package com.ns.springdatajpaquerydsl.repo.many2one;

import com.ns.springdatajpaquerydsl.entity.many2one.SalesOrder;
import com.ns.springdatajpaquerydsl.repo.base.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nanshen
 */
@Repository
public interface SalesOrderRepo extends BaseRepository<SalesOrder, String> {

}
