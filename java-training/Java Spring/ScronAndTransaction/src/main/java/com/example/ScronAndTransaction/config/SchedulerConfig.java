package com.example.ScronAndTransaction.config;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.sql.DataSource;
import java.util.Optional;

@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "10m")
public class SchedulerConfig {

    @Value("${shedlock.enabled}")
    private boolean shedlockEnabled;

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
//        return new ThreadPoolTaskScheduler();

        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5);
        scheduler.setThreadNamePrefix("MyScheduler-");
        return scheduler;
    }

    @Bean
    public LockProvider lockProvider(DataSource dataSource) {
        if (!shedlockEnabled) {
            System.out.println("🔕 ShedLock DISABLED (fake lock)");
            return (lockConfig) -> Optional.of(() -> {}); // Không thực sự lock
        }
        System.out.println("🔐 ShedLock ENABLED (real lock)");
        return new JdbcTemplateLockProvider(
                JdbcTemplateLockProvider.Configuration.builder()
                        .withJdbcTemplate(new JdbcTemplate(dataSource))
                        .usingDbTime()
                        .build()
        );
    }
}
