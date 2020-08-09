package com.ns.jpamultidatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EntityScan(basePackages={"com.ns.jpamultidatasource.entity"})
//@EnableJpaRepositories(basePackages={"com.ns.jpamultidatasource.repoone", "com.ns.jpamultidatasource.repotwo"})
public class JpaMultiDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaMultiDatasourceApplication.class, args);
    }

}
