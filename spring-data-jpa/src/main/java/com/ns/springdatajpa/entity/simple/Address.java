package com.ns.springdatajpa.entity.simple;

import lombok.Data;

import javax.persistence.Embeddable;

/**
 * @author ns
 * @create 2020-05-30
 */
@Data
@Embeddable
public class Address {
	private String address1;
	private String address2;
	private String address3;
	private String city;
	private String province;
	private String country;
	private String phoneNo;
	private String email;
	private String postalCode;
}
