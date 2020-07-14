package com.ns.shiro.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Set;

/**
 * @author ns
 * @create 2020-07-12
 */
@Entity
@Data
public class SysRole extends BaseEntity{
	@ManyToMany
	private Set<SysPermission> permissions;

	public SysRole(String code, String name, Set<SysPermission> permissions) {
		setCode(code);
		setName(name);
		this.permissions = permissions;
	}
}
