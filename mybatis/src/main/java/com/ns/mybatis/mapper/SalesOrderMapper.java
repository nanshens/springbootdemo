package com.ns.mybatis.mapper;

import com.ns.mybatis.entity.SalesOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ns
 * @create 2020-07-16
 */
@Mapper
public interface SalesOrderMapper {
    SalesOrder findById(String soId);
}
