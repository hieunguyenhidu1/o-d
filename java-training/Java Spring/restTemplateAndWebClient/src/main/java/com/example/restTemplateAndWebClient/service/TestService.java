package com.example.restTemplateAndWebClient.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Value("${server.port}")
    private int serverPort;

    public String getSlowServiceUri(){
        return "http://localhost:8080/api/users/slow-service-tweets";
    }
}
