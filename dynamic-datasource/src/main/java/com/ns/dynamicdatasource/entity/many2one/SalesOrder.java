package com.ns.dynamicdatasource.entity.many2one;

import com.ns.dynamicdatasource.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

/**
 * @author ns
 * @create 2020-05-29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class SalesOrder extends BaseEntity {
	private String code;
	private String name;
	private Integer age;
	private Boolean active;
	private LocalDate date;

	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;
}
