package com.ns.shardingspherereadwritemasking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableConfigurationProperties
public class ShardingSphereReadwriteMaskingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereReadwriteMaskingApplication.class, args);
    }

}
