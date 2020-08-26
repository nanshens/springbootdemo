package com.ns.jpamultidatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ServletComponentScan
@SpringBootApplication
public class JpaMultiDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaMultiDatasourceApplication.class, args);
    }

}
