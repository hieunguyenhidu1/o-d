package com.example.beanLifeCycleDemo;

import org.springframework.stereotype.Component;

@Component
public class Boy {
    public Boy(){
        System.out.println("Khoi tao Boy");
    }
}
