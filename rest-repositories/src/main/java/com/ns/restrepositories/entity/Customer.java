package com.ns.restrepositories.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

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
public class Customer extends BaseEntity {
	private String name;
	private Integer age;
	private String tel;
}
