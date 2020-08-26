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
@EnableJpaRepositories(basePackages = "com.ns.jpamultidatasource.repoone",
        entityManagerFactoryRef = "entityManagerFactoryPrimary",
        transactionManagerRef = "transactionManagerPrimary"
)
public class JpaConfigOne {
    @Autowired
    @Qualifier(value = "dsOne")
    DataSource dsOne;

    @Autowired
    JpaProperties jr;

    @Autowired
    HibernateProperties hibernateProperties;

    @Primary
    @Bean(name = "entityManagerFactoryPrimary")
    LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder){
        return builder.dataSource(dsOne)
                .properties(jr.getProperties())
                .properties(getVendorProperties())
                .packages("com.ns.jpamultidatasource.entity")
                .persistenceUnit("pu1")
                .build();

    }

    @Primary
    @Bean(name = "entityManagerPrimary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name="transactionManagerPrimary")
    PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder){
        LocalContainerEntityManagerFactoryBean entityManagerFactory = entityManagerFactoryPrimary(builder);
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }


    

    private Map getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jr.getProperties(), new HibernateSettings());

//        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }
}
