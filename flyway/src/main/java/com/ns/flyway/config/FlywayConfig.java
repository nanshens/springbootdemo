package com.ns.flyway.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ns
 * @create 2020-08-12
 */
@Configuration
@EnableConfigurationProperties(FlywayProperties.class)
public class FlywayConfig {

//    @Bean
//    public void flyway2() {
//        Map<String, DataSource> dataSourceMap = dataSource.getDataSourceMap();
//        for (Map.Entry<String, DataSource> entry : dataSourceMap.entrySet()) {
//            ClassicConfiguration configuration = new ClassicConfiguration();
//            configuration.setBaselineOnMigrate(true);
//            configuration.setDataSource(entry.getValue());
//            configuration.setCleanOnValidationError(true);
//            org.flywaydb.core.Flyway flyway = new org.flywaydb.core.Flyway(configuration);
//            flyway.migrate();
//        }
//
//    }

    @Bean
    @ConfigurationProperties(prefix = "flyway")
    public Flyway flyway() {
        // you also can use java bean to config.


        return Flyway.configure()
                .dataSource("jdbc:postgresql://127.0.0.1:5432/demo_flyway", "postgres", "dl123")
                .baselineVersion("1")
                .load();
    }

    @Bean
    @ConditionalOnMissingBean
    public FlywayMigrationInitializer flywayInitializer(Flyway flyway) {
        return new FlywayMigrationInitializer(flyway, null);
    }
}
