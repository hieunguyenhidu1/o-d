package com.example.migration.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {
    @Value("${spring.flyway.enabled:true}")
    private boolean flywayEnabled;

    @Value("${spring.flyway.locations:classpath:db/migration}")
    private String locations;

    @Value("${spring.flyway.baseline-on-migrate:true}")
    private boolean baselineOnMigrate;

    @Value("${spring.flyway.table:flyway_schema_history}")
    private String flywayTable;

    /**
     * Khởi tạo Flyway Bean, thực hiện migrate khi ứng dụng khởi động.
     */
    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource) {
        if (!flywayEnabled) {
            System.out.println("⚠ Flyway is disabled via configuration");
            return null;
        }

        System.out.println("✅ Running Flyway migration...");

        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations(locations)               // Thư mục chứa file migration SQL
                .baselineOnMigrate(baselineOnMigrate)
                .baselineVersion("1")
                .table(flywayTable)
                .load();
        // Neu co initMethod = "migrate" thi khong can dung
        // flyway.migrate();
        return flyway;
    }
}
