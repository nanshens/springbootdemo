package com.ns.shardingspherereadwritemasking.config;

import com.google.common.collect.Lists;
import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.encrypt.api.EncryptColumnRuleConfiguration;
import org.apache.shardingsphere.encrypt.api.EncryptRuleConfiguration;
import org.apache.shardingsphere.encrypt.api.EncryptTableRuleConfiguration;
import org.apache.shardingsphere.encrypt.api.EncryptorRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author ns
 * @create 2020-08-22
 */
@Component
public class SphereAndReadWriteAndMaskingDSConfig {
    @Autowired
    private DataSourceFactory dataSourceFactory;

    DataSource getDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTableRuleConfiguration());
        shardingRuleConfig.getBindingTableGroups().add("sales_order");
        shardingRuleConfig.getBindingTableGroups().add("sales_order_line");
        shardingRuleConfig.getBroadcastTables().add("customer");
        shardingRuleConfig.getBroadcastTables().add("item");
//        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", new PreciseModuloShardingDatabaseAlgorithm()));
//        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id", new PreciseModuloShardingTableAlgorithm()));
//        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", new ModuloShardingTableAlgorithm()));
        shardingRuleConfig.setMasterSlaveRuleConfigs(getMasterSlaveRuleConfigurations());
        shardingRuleConfig.setEncryptRuleConfig(getEncryptRuleConfiguration());
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new Properties());
    }

    private static KeyGeneratorConfiguration getKeyGeneratorConfiguration() {
        KeyGeneratorConfiguration result = new KeyGeneratorConfiguration("SNOWFLAKE", "id");
        return result;
    }

    TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("sales_order", "ds${0..1}.sales_order_${0..1}");
        result.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "sales_order_$->{id % 2}"));
        result.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "ds$->{id % 2}"));
//        result.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", new ModuloShardingTableAlgorithm()));
        return result;
    }

    TableRuleConfiguration getOrderItemTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("sales_order_line", "ds${0..1}.sales_order_line_${0..1}");
        result.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("sales_order_id", "sales_order_$->{sales_order_id % 2}"));
        result.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("sales_order_id", "ds$->{sales_order_id % 2}"));
//        result.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("sales_order_id", new ModuloShardingTableAlgorithm()));
        return result;
    }
    TableRuleConfiguration getItemTableRuleConfig() {
        TableRuleConfiguration result = new TableRuleConfiguration("item", "ds${0..1}.item");
//        result.setEncryptorConfig(new EncryptorConfiguration("MD5", "status", new Properties()));
        return result;
    }


    List<MasterSlaveRuleConfiguration> getMasterSlaveRuleConfigurations() {
        MasterSlaveRuleConfiguration masterSlaveRuleConfig1 = new MasterSlaveRuleConfiguration("ds0", "ds_master_0", Arrays.asList("ds_master_0_slave_0", "ds_master_0_slave_1"));
        MasterSlaveRuleConfiguration masterSlaveRuleConfig2 = new MasterSlaveRuleConfiguration("ds1", "ds_master_1", Arrays.asList("ds_master_1_slave_0", "ds_master_1_slave_1"));
        return Lists.newArrayList(masterSlaveRuleConfig1, masterSlaveRuleConfig2);
    }

    private static EncryptRuleConfiguration getEncryptRuleConfiguration() {
        Properties props = new Properties();
        props.setProperty("aes.key.value", "123456");
        EncryptorRuleConfiguration encryptorConfig = new EncryptorRuleConfiguration("aes", props);
        EncryptColumnRuleConfiguration columnConfig = new EncryptColumnRuleConfiguration("", "name", "", "aes");
        EncryptTableRuleConfiguration tableConfig = new EncryptTableRuleConfiguration(Collections.singletonMap("name", columnConfig));
        EncryptRuleConfiguration encryptRuleConfig = new EncryptRuleConfiguration();
        encryptRuleConfig.getEncryptors().put("aes", encryptorConfig);
        encryptRuleConfig.getTables().put("item", tableConfig);
        return encryptRuleConfig;
    }

    Map<String, DataSource> createDataSourceMap() {
        final Map<String, DataSource> result = new HashMap<>(6);
        result.put("ds_master_0", dataSourceFactory.dsMaster0());
        result.put("ds_master_0_slave_0", dataSourceFactory.dsMaster0Slave0());
        result.put("ds_master_0_slave_1", dataSourceFactory.dsMaster0Slave1());
        result.put("ds_master_1", dataSourceFactory.dsMaster1());
        result.put("ds_master_1_slave_0", dataSourceFactory.dsMaster1Slave0());
        result.put("ds_master_1_slave_1", dataSourceFactory.dsMaster1Slave1());
        return result;
    }
}
