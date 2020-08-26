package com.ns.shardingjdbccore.config;

import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author ns
 * @create 2020-08-22
 */
@Component
public class ReadWriteSplitDSConfig {
    @Autowired
    private DataSourceFactory dataSourceFactory;

    DataSource getDataSource() throws SQLException {
        MasterSlaveRuleConfiguration masterSlaveRuleConfig = new MasterSlaveRuleConfiguration("ds_master_slave", "ds_master_0", Arrays.asList("ds_master_0_slave_0", "ds_master_0_slave_1"));
        return MasterSlaveDataSourceFactory.createDataSource(createDataSourceMap(), masterSlaveRuleConfig, new Properties());
    }

    Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>(3);
        result.put("ds_master_0", dataSourceFactory.dsMaster0());
        result.put("ds_master_0_slave_0", dataSourceFactory.dsSlave0());
        result.put("ds_master_0_slave_1", dataSourceFactory.dsSlave1());
        return result;
    }
}
