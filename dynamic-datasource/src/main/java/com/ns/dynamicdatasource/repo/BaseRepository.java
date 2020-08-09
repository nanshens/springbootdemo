package com.ns.dynamicdatasource.repo;

import com.ns.dynamicdatasource.entity.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author nanshen
 *
 * 通用接口添加通用方法及继承jpa基础接口
 * jparepository包括一些通用方法
 * JpaSpecificationExecutor 添加复杂查询的能力
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
//	Optional<T> findByCode(String code);
}
