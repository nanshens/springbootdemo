package com.ns.jpamultidatasource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author ns
 * @create 2020-08-08
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.ns.jpamultidatasource.repotwo",
        entityManagerFactoryRef = "entityManagerFactorySecondary",
        transactionManagerRef = "transactionManagerSecondary")
public class JpaConfigTwo {
    @Autowired
    @Qualifier(value = "dsTwo")
    DataSource dsTwo;

    @Autowired
    JpaProperties jr;

    @Autowired
    HibernateProperties hibernateProperties;

    @Bean(name = "entityManagerFactorySecondary")
    LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder){
        return builder.dataSource(dsTwo)
                .properties(jr.getProperties())
                .properties(getVendorProperties())
                .packages("com.ns.jpamultidatasource.entity")
                .persistenceUnit("pu2")
                .build();

    }

    @Bean(name = "entityManagerSecondary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySecondary(builder).getObject().createEntityManager();
    }

    @Bean(name="transactionManagerSecondary")
    PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder){
        LocalContainerEntityManagerFactoryBean entityManagerFactory = entityManagerFactorySecondary(builder);
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }



    private Map getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jr.getProperties(), new HibernateSettings());

//        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }
}
