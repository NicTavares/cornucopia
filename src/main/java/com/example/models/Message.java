package com.example.models;

import java.sql.Date;

public class Message {

 
  
    private int UUID;
    private String text;
    private int senderUUID;
    private String sentTime;
    private int receiverUUID;

    public Message(int UUID,
                   String text,
                   int senderUUID,
                   String sentTime,
                   int receiverUUID) {
        this.UUID = UUID;
        this.text = text;
        this.senderUUID = senderUUID;
        this.sentTime = sentTime;
        this.receiverUUID = receiverUUID;
    }

    public int getUUID() {
        return UUID;
    }

    public String getText() {
        return text;
    }

    public String getSentTime() {
        return sentTime;
    }

    public int getReceiverUUID() {
        return receiverUUID;
    }

    public int getSenderUUID() {
        return senderUUID;
    }

    public void setUUID(int UUID) {
        this.UUID = UUID;
    }
}
