package com.example.intechchat.controllers;


import com.example.intechchat.domain.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/api/v1/chat")
    @SendTo("/api/v1/topic/messages")
    public ChatMessage send(String message) {
        return ChatMessage.create(SecurityContextHolder.getContext().getAuthentication().getName(), message);
    }
}
