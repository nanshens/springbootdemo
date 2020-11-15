package com.ns.springboot.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * @author ns
 * @create 2020-07-04
 */
@Entity
@Data
public class Customer {
	@Id
	@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
	@GeneratedValue(generator = "jpa-uuid")
	private String id;
	private String code;
	private Integer version;
}
