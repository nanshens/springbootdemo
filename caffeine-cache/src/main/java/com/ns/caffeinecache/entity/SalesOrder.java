package com.ns.caffeinecache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ns
 * @create 2020-07-15
 */
@Data
@AllArgsConstructor
public class SalesOrder {
    private String id;
    private String name;
    private String code;
}
