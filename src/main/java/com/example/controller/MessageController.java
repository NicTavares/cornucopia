package com.example.controller;

import com.example.DAO.MessageDAO;
import com.example.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    MessageDAO messageDAO;

    @GetMapping(path="/getMessage/{usrUUID}")
    public List<Message> getMessage(@PathVariable int usrUUID)
    {
        return messageDAO.getMessageInbox(Integer.toString(usrUUID));
    }

    @PostMapping(path="/sendMessage")
    public ResponseEntity sendMessage(@RequestBody Message message)

    {
        message.setUUID(messageDAO.getNextUUID());
        messageDAO.create(message);
        return ResponseEntity.ok("Message sent");
    }

//    {
//        "UUID":0,
//            "text":"Hi",
//            "senderUUID":11112,
//            "sentTime":"2021-05-04 23:33:00",
//            "receiverUUID": 11111
//    }
}
