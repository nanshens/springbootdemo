package com.ns.shardingspherereadwritemasking.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @author ns
 * @create 2020-08-23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Item extends BaseEntity {
    private String name;
    private String unit;
    private String namePlain;
    private String nameCipher;
}
