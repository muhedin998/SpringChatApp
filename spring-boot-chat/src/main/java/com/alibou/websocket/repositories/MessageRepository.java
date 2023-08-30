package com.alibou.websocket.repositories;

import com.alibou.websocket.models.AppUser;
import com.alibou.websocket.models.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository  extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findBySenderAndReceiver(
            AppUser sender, AppUser receiver
    );
}
