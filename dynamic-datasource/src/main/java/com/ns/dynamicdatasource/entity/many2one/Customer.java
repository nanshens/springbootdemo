package com.ns.dynamicdatasource.entity.many2one;

import com.ns.dynamicdatasource.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @author ns
 * @create 2020-05-29
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
