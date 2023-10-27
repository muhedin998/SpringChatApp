package com.socket.socket.controller;

import com.socket.socket.models.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpleMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        System.out.println(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/private-message")
    public ChatMessage privateChatMessage (@Payload ChatMessage chatMessage) {
        simpleMessagingTemplate.convertAndSendToUser(chatMessage.getReciever(), "/private", chatMessage);
        return chatMessage;
    }

}