package com.ns.mybatis.entity;

import lombok.Data;

/**
 * @author ns
 * @create 2020-07-15
 */
@Data
public class SalesOrder {
    private String id;
    private String name;
    private String code;
    private Customer customer;
}
