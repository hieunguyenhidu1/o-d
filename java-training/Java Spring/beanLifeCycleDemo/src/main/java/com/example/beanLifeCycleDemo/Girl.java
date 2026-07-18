package com.example.beanLifeCycleDemo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Girl {
    @Autowired
    private Boy boy;

    @PostConstruct
    public void init(){
        System.out.println("Post girl");
    }

    public Girl(){
        System.out.println("Girl dang khoi tao");
    }

    public void useBean(){
        System.out.println("Use girl");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Destroy Girl");
    }

}
