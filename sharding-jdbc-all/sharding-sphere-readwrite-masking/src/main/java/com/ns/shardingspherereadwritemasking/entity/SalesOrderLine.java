package com.ns.shardingspherereadwritemasking.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 * @author ns
 * @create 2020-08-23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class SalesOrderLine extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private SalesOrder salesOrder;
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal cost;
}
