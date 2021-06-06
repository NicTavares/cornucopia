package com.example.models;

import java.util.Date;

public class Message {

    private int UUID;
    private String text;
    private int senderUUID;
    private Date sentTime;
    private int receiverUUID;

    public Message(int UUID,
                   String text,
                   int senderUUID,
                   Date sentTime,
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

    public Date getSentTime() {
        return sentTime;
    }

    public int getReceiverUUID() {
        return receiverUUID;
    }

    public int getSenderUUID() {
        return senderUUID;
    }
}
