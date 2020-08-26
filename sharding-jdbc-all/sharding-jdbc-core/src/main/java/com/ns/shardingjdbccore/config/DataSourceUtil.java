package com.ns.shardingjdbccore.config;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 * @author ns
 * @create 2020-08-22
 */

public class DataSourceUtil {
    private static final String HOST = "localhost";

    private static final int PORT = 5432;

    private static final String USER_NAME = "postgres";

    private static final String PASSWORD = "dl123";

    public static DataSource createDataSource(final String dataSourceName) {
        HikariDataSource result = new HikariDataSource();
        result.setDriverClassName("org.postgresql.Driver");
//        result.setJdbcUrl("jdbc:postgresql://127.0.0.1:5432/demo_sharding_master");
        result.setJdbcUrl(String.format("jdbc:postgresql://%s:%s/%s", HOST, PORT, dataSourceName));
        result.setUsername(USER_NAME);
        result.setPassword(PASSWORD);
        return result;
    }
}
