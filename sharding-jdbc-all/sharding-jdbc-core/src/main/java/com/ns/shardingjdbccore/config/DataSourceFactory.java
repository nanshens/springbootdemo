package com.ns.shardingjdbccore.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author ns
 * @create 2020-08-25
 */
@Configuration
public class DataSourceFactory {
    @Bean
    @ConfigurationProperties(prefix = "sharding.datasource.master0")
    DataSource dsMaster0() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "sharding.datasource.slave0")
    DataSource dsSlave0() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "sharding.datasource.slave1")
    DataSource dsSlave1() {
        return DataSourceBuilder.create().build();
    }
}
