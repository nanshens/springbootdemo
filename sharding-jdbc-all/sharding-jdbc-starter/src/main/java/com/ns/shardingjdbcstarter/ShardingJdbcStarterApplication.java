package com.ns.shardingjdbcstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ShardingJdbcStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcStarterApplication.class, args);
    }

}
