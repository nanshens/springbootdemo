package com.ns.shiro.entity;

import lombok.Data;

import javax.persistence.Entity;

/**
 * @author ns
 * @create 2020-07-12
 */
@Entity
@Data
public class SysPermission extends BaseEntity{
	public SysPermission(String code, String name) {
		setCode(code);
		setName(name);
	}
}
