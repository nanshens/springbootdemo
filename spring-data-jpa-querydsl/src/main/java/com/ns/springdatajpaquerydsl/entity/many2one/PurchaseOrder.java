package com.ns.springdatajpaquerydsl.entity.many2one;

import com.ns.springdatajpaquerydsl.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

/**
 * @author ns
 * @create 2020-05-29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class PurchaseOrder extends BaseEntity {
	private String soId;
	private String code;
	private Integer number;
	private LocalDate date;
	private Boolean active;
	@Enumerated(EnumType.STRING)
	private Status status;
}
