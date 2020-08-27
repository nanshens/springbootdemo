package com.ns.shardingspherereadwritemasking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.SharedEntityManagerCreator;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author ns
 * @create 2020-08-21
 */

@Configuration
public class DataSourceConfig {

    @Autowired
    JpaProperties jr;

    @Autowired
    HibernateProperties hibernateProperties;

    @Autowired
    SphereAndReadWriteAndMaskingDSConfig shardingDSConfig;

    @Primary
    @Bean("dataSource")
    public DataSource dataSource() throws SQLException {
        return shardingDSConfig.getDataSource();
    }

    @Primary
    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) throws SQLException {
        return builder.dataSource(dataSource())
                .properties(jr.getProperties())
                .properties(getVendorProperties())
                .packages("com.ns.shardingspherereadwritemasking.entity")
                .persistenceUnit("s1")
                .build();

    }

    @Bean
    @Primary
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory){
        return SharedEntityManagerCreator.createSharedEntityManager(entityManagerFactory);
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    private Map getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jr.getProperties(), new HibernateSettings());
//        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }
}
