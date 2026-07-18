package com.example.beanDemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.stereotype.Component;

//@Component
public class Boy {
    @Autowired
    private ObjectMapper objectMapper;

    public void useObjectMapper(){
        System.out.println("Object mapper: " + objectMapper);
    }
}
