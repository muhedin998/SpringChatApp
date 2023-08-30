package com.alibou.websocket.services.Impl;

import com.alibou.websocket.models.ChatMessage;
import com.alibou.websocket.services.ChatMessageService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ChatMessageImpl implements ChatMessageService {

    @Override
    public ChatMessage createChat(ChatMessage user) {
        return null;
    }

    @Override
    public List<ChatMessage> getMessagesByReciever(String phoneNumber) {
        return null;
    }

    @Override
    public ChatMessage deleteMessage(ChatMessage user) {
        return null;
    }

    @Override
    public List<ChatMessage> getAllMessages() {
        return null;
    }
}
