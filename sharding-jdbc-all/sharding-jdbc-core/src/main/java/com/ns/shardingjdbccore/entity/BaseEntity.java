package com.ns.shardingjdbccore.entity;

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
	@GenericGenerator(name = "snowflake", strategy = "com.ns.shardingjdbccore.config.CustomIdGenerator")
	@GeneratedValue(generator = "snowflake")
	private long id;
}
