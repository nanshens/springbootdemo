package com.ns.dubbointerface.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author ns
 * @create 2020-05-29
 */
@Data
@NoArgsConstructor
public class Customer implements Serializable {
	private String name;
	private Integer age;
	private String tel;

}
