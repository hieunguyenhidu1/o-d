package com.example.iocDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Client {
    @Autowired
    private MessageService messageService;

//    public Client(MessageService messageService){
//        this.messageService = messageService;
//    }

    public void processService(String message){
        messageService.sendMessage(message);
    }
}
