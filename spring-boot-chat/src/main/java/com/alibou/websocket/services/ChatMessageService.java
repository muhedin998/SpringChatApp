package com.alibou.websocket.services;

import com.alibou.websocket.models.AppUser;
import com.alibou.websocket.models.ChatMessage;

import java.util.List;

public interface ChatMessageService {
    ChatMessage createChat(ChatMessage user);

    List<ChatMessage> getPrivateMessages(AppUser sender, AppUser reciever);

    ChatMessage deleteMessage(ChatMessage user);

    List<ChatMessage> getAllMessages();
}
