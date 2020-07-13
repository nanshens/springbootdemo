package com.ns.springsecurity.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author ns
 * @create 2020-06-28
 */

@Entity
@Data
public class SystemUser {
	@Id
	@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
	@GeneratedValue(generator = "jpa-uuid")
	private String id;
	@Column(unique = true)
	private String username;
	private String password;
}
