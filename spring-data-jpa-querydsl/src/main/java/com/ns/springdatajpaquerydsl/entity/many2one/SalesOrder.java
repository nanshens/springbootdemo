package com.ns.springdatajpaquerydsl.entity.many2one;

import com.ns.springdatajpaquerydsl.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import java.time.LocalDate;

/**
 * @author ns
 * @create 2020-05-29
 *
 * N+1问题:
 * 需要在entity用注解NamedEntityGraph注释,自定义名字,和关联查询的字段
 * 在repository关联到查询方法(注意: EntityGraph只能注解到基本方法,命名方法,jpql的查询方法. 不能在使用nativequery的方法上注解)
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@NamedEntityGraph(name = "SalesOrder.find", attributeNodes = { @NamedAttributeNode("customer") })
public class SalesOrder extends BaseEntity {
	private String code;
	private String name;
	private Integer age;
	private Boolean active;
	private LocalDate date;
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;
}
