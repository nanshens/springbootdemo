package com.ns.shardingjdbccore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class ShardingJdbcCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcCoreApplication.class, args);
    }

}
