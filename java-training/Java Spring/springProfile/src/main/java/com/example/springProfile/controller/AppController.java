package com.example.springProfile.controller;

import com.example.springProfile.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:config.properties")
public class AppController {
    @Autowired
    private Environment envGlobal;

    @Value("${app.name}")
    private String name;
    @Value("${app.environment}")
    private String env;
//    @Value("${app.url}")
//    private String url;

    @Value("${message}")
    private String message;

    private final AppConfig appConfig;

    public AppController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @GetMapping("/config")
    public String getConfig() {
//        return String.format("Name: %s | Env: %s | URL: %s",
//                appConfig.getName(),
//                appConfig.getEnvironment(),
//                appConfig.getUrl());
        return String.format("Name: %s | Env: %s | URL: %s | Message: %s",
                name,
                env,
                envGlobal.getProperty("app.url"),
                message);
    }
}
