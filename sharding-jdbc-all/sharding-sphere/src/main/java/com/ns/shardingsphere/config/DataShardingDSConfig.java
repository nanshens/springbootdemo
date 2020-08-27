package com.ns.shardingsphere.config;

import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author ns
 * @create 2020-08-22
 */
@Component
public class DataShardingDSConfig {
    @Autowired
    private DataSourceFactory dataSourceFactory;

    DataSource getDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTableRuleConfiguration());
        shardingRuleConfig.getBindingTableGroups().add("sales_order,sales_order_line");
//        shardingRuleConfig.getBroadcastTables().add("customer");
        shardingRuleConfig.setDefaultDataSourceName("ds0");
//        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "ds0"));
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", new ModuloShardingTableAlgorithm()));
//        shardingRuleConfig.setDefaultKeyGeneratorConfig(getKeyGeneratorConfiguration());
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new Properties());
    }

    private static KeyGeneratorConfiguration getKeyGeneratorConfiguration() {
        KeyGeneratorConfiguration result = new KeyGeneratorConfiguration("SNOWFLAKE", "id");
        return result;
    }

    TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("sales_order", "ds0.sales_order_${0..2}");
//        TableRuleConfiguration result = new TableRuleConfiguration("sales_order", "ds${0..1}.sales_order_${0..1}");
//        result.setKeyGeneratorConfig(getKeyGeneratorConfiguration());
        result.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", new ModuloShardingTableAlgorithm()));
        return result;
    }

    TableRuleConfiguration getOrderItemTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("sales_order_line", "ds0.sales_order_line_${0..2}");
        result.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("sales_order_id", new ModuloShardingTableAlgorithm()));
        return result;
    }

    Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>(3);
        result.put("ds0", dataSourceFactory.ds0());
        return result;
    }
}
