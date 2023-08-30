package com.alibou.websocket.repositories;

import com.alibou.websocket.models.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository  extends JpaRepository<ChatMessage, Long> {
}
