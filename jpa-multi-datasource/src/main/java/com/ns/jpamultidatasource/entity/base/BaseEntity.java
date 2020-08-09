package com.ns.jpamultidatasource.entity.base;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author ns
 * @create 2020-05-29
 */
@Data
@MappedSuperclass
public abstract class BaseEntity {
	@Id
	@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
	@GeneratedValue(generator = "jpa-uuid")
	private String id;
}
