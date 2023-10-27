package com.socket.socket.models;

import lombok.Data;

@Data
public class ChatMessage {

    private String content;
    private String sender;
    private String reciever;
    private String date;
    private Status status;
    // Getters and setters
}
