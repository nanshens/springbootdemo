package com.ns.springdatajpa.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ns
 * @create 2020-05-31
 */
@Data
@NoArgsConstructor
public class SalesOrderVO {
	private String code;
	private String name;
	private String customerName;

	@Tag
	public SalesOrderVO(String code, String name, String customerName) {
		this.code = code;
		this.name = name;
		this.customerName = customerName;
	}

	@Tag(1)
	public SalesOrderVO(String code, String name) {
		this.code = code;
		this.name = name;
	}

}
