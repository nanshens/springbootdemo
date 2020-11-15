package com.ns.dubbointerface.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author ns
 * @create 2020-05-29
 */
@Data
@NoArgsConstructor
public class SalesOrder implements Serializable {
	private String code;
	private String name;
	private Integer age;
	private Boolean active;
	private LocalDate date;
	private Customer customer;
}
