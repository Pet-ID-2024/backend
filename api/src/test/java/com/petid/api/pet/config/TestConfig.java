package com.petid.api.pet.config;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@TestConfiguration
public class TestConfig {
    @Bean
    public IDatabaseConnection dbUnitDatabaseConnection(DataSource dataSource) throws Exception {
        return new DatabaseConnection(dataSource.getConnection());
    }
}
