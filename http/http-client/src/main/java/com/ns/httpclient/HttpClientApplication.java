package com.ns.httpclient;

import com.thebeastshop.forest.springboot.annotation.ForestScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ForestScan(basePackages = "com.ns.httpclient.forestclient")
public class HttpClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpClientApplication.class, args);
    }

}
