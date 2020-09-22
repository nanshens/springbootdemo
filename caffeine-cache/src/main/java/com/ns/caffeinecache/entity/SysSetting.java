package com.ns.caffeinecache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author ns
 * @create 2020-09-20
 */
@Data
@AllArgsConstructor
public class SysSetting {
    private String name;
    private boolean primary;
    private String email;
    private String address;
    private LocalDate date;
}
