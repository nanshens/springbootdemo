package com.ns.springdatajpa.entity.simple;

import com.ns.springdatajpa.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * @author ns
 * @create 2020-05-29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Simple extends BaseEntity {
	private String code;
}
