package com.ns.shiro.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * @author ns
 * @create 2020-07-12
 */
@Entity
@Data
public class SysUser extends BaseEntity{
	private String password;
	@ManyToMany
	private Set<SysRole> roles;
	public SysUser(String code, String name, String password, Set<SysRole> roles) {
		setCode(code);
		setName(name);
		this.roles = roles;
		this.password = password;
	}
}
