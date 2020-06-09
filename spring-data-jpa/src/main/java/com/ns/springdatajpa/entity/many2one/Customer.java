package com.ns.springdatajpa.entity.many2one;

import com.ns.springdatajpa.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author ns
 * @create 2020-05-29
 * NamedQueries, NamedQuery 定义命名查询,两种方式调用
 * 1. name和repository中方法的名字匹配, 可以自动映射
 * 2. 使用entityManager.createNamedQuery("findCustomerWithId")调用
 *
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@NamedQueries({
		@NamedQuery(name="findCustomerWithId", query="SELECT c FROM Customer c WHERE c.id=?1"),
		@NamedQuery(name="findCustomerWithName", query="SELECT c FROM Customer c WHERE c.name = :name")
})
@NamedQuery(name="findCustomerByTel", query="SELECT c FROM Customer c WHERE c.tel=?1")
public class Customer extends BaseEntity {
	private String name;
	private Integer age;
	private String tel;
}
