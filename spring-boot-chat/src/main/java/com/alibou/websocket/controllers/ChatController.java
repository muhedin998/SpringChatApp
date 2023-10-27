package com.alibou.websocket.controllers;

import com.alibou.websocket.models.AppUser;
import com.alibou.websocket.models.ChatMessage;
import com.alibou.websocket.services.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@CrossOrigin("*")
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatMessageService chatMessageService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessageService.createChat(chatMessage);
    }

    @GetMapping("/topic/initial")
    public List<ChatMessage> getInitialMessages () {
        return chatMessageService.getAllMessages();
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/private")
    @SendToUser("/queue/private")
    public ChatMessage handlePrivateMessage(@Payload ChatMessage message) {
        // Process private message and send it to recipient's private queue
        chatMessageService.createChat(message);
        // messagingTemplate.convertAndSendToUser();
        return message;
    }

    @GetMapping("/private/messages")
    public ResponseEntity<List<ChatMessage>> getPrivateMessages (@RequestBody ChatMessage message) {
        return ResponseEntity.ok(chatMessageService.getPrivateMessages(message.getSender(), message.getReceiver()));
    }
}
