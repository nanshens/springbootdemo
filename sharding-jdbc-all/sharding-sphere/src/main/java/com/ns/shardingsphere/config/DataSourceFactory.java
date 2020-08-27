package com.ns.shardingsphere.config;

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
    @ConfigurationProperties(prefix = "sharding.datasource.ds0")
    DataSource ds0() {
        return DataSourceBuilder.create().build();
    }

}
