package com.ns.shardingspherereadwritemasking.config;

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
    @ConfigurationProperties(prefix = "sharding.datasource.master0slave0")
    DataSource dsMaster0Slave0() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "sharding.datasource.master0slave1")
    DataSource dsMaster0Slave1() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "sharding.datasource.master1")
    DataSource dsMaster1() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "sharding.datasource.master1slave0")
    DataSource dsMaster1Slave0() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "sharding.datasource.master1slave1")
    DataSource dsMaster1Slave1() {
        return DataSourceBuilder.create().build();
    }
}
