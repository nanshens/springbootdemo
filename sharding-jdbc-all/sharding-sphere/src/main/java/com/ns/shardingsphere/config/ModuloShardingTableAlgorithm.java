package com.ns.shardingsphere.config;

import com.google.common.collect.Range;
import org.apache.logging.log4j.util.Strings;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author ns
 * @create 2020-08-23
 */

public class ModuloShardingTableAlgorithm implements PreciseShardingAlgorithm<Long>, RangeShardingAlgorithm<Long> {

    /**
     * 选表
     */
    @Override
    public String doSharding(final Collection<String> tableNames, final PreciseShardingValue<Long> shardingValue) {

        String tableName = "";

//        if ("customer".equalsIgnoreCase(shardingValue.getLogicTableName())) {
//            for (String each : tableNames) {
//                if (each.endsWith(shardingValue.getValue() % tableNames.size() + "")) {
//                    tableName = each;
//                    break;
//                }
//            }
//        }

        // 分库分表
//        if ("sales_order".equalsIgnoreCase(shardingValue.getLogicTableName()) ||
//                "customer".equalsIgnoreCase(shardingValue.getLogicTableName())) {
//            for (String each : tableNames) {
//                if (each.endsWith(shardingValue.getValue() % tableNames.size() + "")) {
//                    tableName = each;
//                    break;
//                }
//            }
//        }

        for (String each : tableNames) {
            if (each.endsWith(shardingValue.getValue() % tableNames.size() + "")) {
                tableName = each;
                break;
            }
        }

        if (!Strings.isEmpty(tableName)) {
            return tableName;
        }

        throw new UnsupportedOperationException();
    }

    /**
     * 实现between and查询
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {

        Collection<String> collect = new ArrayList<>();
        Range<Long> valueRange = rangeShardingValue.getValueRange();
        for (Long i = valueRange.lowerEndpoint(); i <= valueRange.upperEndpoint(); i++) {
            for (String each : collection) {
                if (each.endsWith(i % collection.size() + "")) {
                    collect.add(each);
                }
            }
        }

        return collect;
    }
}