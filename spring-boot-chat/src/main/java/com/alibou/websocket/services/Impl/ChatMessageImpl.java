package com.alibou.websocket.services.Impl;

import com.alibou.websocket.models.AppUser;
import com.alibou.websocket.models.ChatMessage;
import com.alibou.websocket.repositories.MessageRepository;
import com.alibou.websocket.services.ChatMessageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ChatMessageImpl implements ChatMessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public ChatMessage createChat(ChatMessage msg) {
        return messageRepository.save(msg);
    }

    @Override
    public List<ChatMessage> getPrivateMessages(AppUser sender, AppUser reciever) {
        return messageRepository.findBySenderAndReceiver(sender, reciever);
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
