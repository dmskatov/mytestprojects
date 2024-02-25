package org.example.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class ConnectionToDB {

    @Bean
    public JdbcTemplate template (DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
