package com.example.models;

public class MessagePayload {


    private String senderUsrName;
    private String receiverUsrName;
    private String text;
    private String sentTime;

    public MessagePayload(){
        super();
    }
    public MessagePayload(String senderUsrName,
            String receiverUsrName,
            String text,
            String sentTime){
        this.senderUsrName=senderUsrName;
        this.receiverUsrName=receiverUsrName;
        this.text=text;
        this.sentTime=sentTime;
    }

    public String getReceiverUsrName() {
        return receiverUsrName;
    }

    public String getText() {
        return text;
    }

    public String getSenderUsrName() {
        return senderUsrName;
    }

    public String getSentTime() {
        return sentTime;
    }

    public void setReceiverUsrName(String receiverUsrName) {
        this.receiverUsrName = receiverUsrName;
    }

    public void setSenderUsrName(String senderUsrName) {
        this.senderUsrName = senderUsrName;
    }

    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }

    public void setText(String text) {
        this.text = text;
    }
}
