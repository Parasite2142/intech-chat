package com.example.intechchat.controllers;


import com.example.intechchat.dto.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/publish")
    @SendTo("/users/publish")
    public ChatMessage send(ChatMessage message) {
        return message;
    }
}
