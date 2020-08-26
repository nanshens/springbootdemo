package com.ns.shardingjdbccore.config;

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

    @Autowired DataShardingDSConfig shardingDSConfig;
    @Autowired ShardingAndReadWriteDSConfig shardingAndReadWriteDSConfig;

//    @Bean
//    @ConfigurationProperties(prefix = "sharding.datasource.master0")
//    DataSource ds0() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "sharding.datasource.slave0")
//    DataSource ds1() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "sharding.datasource.slave1")
//    DataSource ds2() {
//        return DataSourceBuilder.create().build();
//    }

//    @Bean
//    @ConfigurationProperties(prefix = "sharding.datasource.master0")
//    DataSource dsMaster0() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "sharding.datasource.slave0")
//    DataSource dsSlave0() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "sharding.datasource.slave1")
//    DataSource dsSlave1() {
//        return DataSourceBuilder.create().build();
//    }




    @Primary
    @Bean("dataSource")
    public DataSource dataSource() throws SQLException {
        return shardingAndReadWriteDSConfig.getDataSource();
    }

    @Primary
    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) throws SQLException {
        return builder.dataSource(dataSource())
                .properties(jr.getProperties())
                .properties(getVendorProperties())
                .packages("com.ns.shardingjdbc.entity")
                .persistenceUnit("s1")
                .build();

    }
//    public EntityManagerFactory entityManagerFactory() throws SQLException {
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPersistenceUnitName("default");
//        factory.setPackagesToScan(Constants.BASE_PACKAGES);
//        factory.setDataSource(dataSource());
//        factory.setJpaPropertyMap(jpaProperties.getProperties());
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }

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
