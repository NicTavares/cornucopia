package com.example.controller;

import com.example.DAO.MessageDAO;
import com.example.DAO.UsrDAO;
import com.example.models.Message;
import com.example.models.MessagePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    MessageDAO messageDAO;
    @Autowired
    UsrDAO usrDAO;

    @GetMapping(path="/getMessage/{usrUUID}")
    public List<Message> getMessage(@PathVariable int usrUUID)
    {
        return messageDAO.getMessageInbox(Integer.toString(usrUUID));
    }

    @GetMapping(path="/getMessagesByUsername/{username}")
    public List<Message> getMessagesByUsername(@PathVariable String username)
    {
        return messageDAO.getMessageInboxByUsername(username);
    }

    @PostMapping(path="/sendMessage")
    public ResponseEntity sendMessage(@RequestBody MessagePayload messagePayload)
    {
        int senderUUID=usrDAO.getUUIDByUsername(messagePayload.getSenderUsrName());
        int receiverUUID=usrDAO.getUUIDByUsername(messagePayload.getReceiverUsrName());
        Message message=new Message(0,messagePayload.getText(),senderUUID,messagePayload.getSentTime(),receiverUUID);
        message.setUUID(messageDAO.getNextUUID());
        messageDAO.create(message);
        return ResponseEntity.ok("Message sent");
    }
//    {
//        "senderUsrName":"gloria",
//            "receiverUsrName":"polly",
//            "text":"test API msg",
//            "sentTime":"2022-01-01"
//    }

//    {
//        "UUID":0,
//            "text":"Hi",
//            "senderUUID":11112,
//            "sentTime":"2021-05-04 23:33:00",
//            "receiverUUID": 11111
//    }
}
