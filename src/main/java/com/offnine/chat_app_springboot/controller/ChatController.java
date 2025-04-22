package com.offnine.chat_app_springboot.controller;

import java.time.LocalDateTime;

import com.offnine.chat_app_springboot.Request.MessageRequest;
import com.offnine.chat_app_springboot.entites.Message;
import com.offnine.chat_app_springboot.entites.Room;
import com.offnine.chat_app_springboot.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin("*") // Allow all origins for Cross-Origin Requests
public class ChatController {

    @Autowired
    private RoomRepo roomRepo;

    // For sending and receiving messages
    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}") // Subscribe to the message by client
    public Message sendMessage(
            @RequestBody MessageRequest req,
            @DestinationVariable String roomId
    ) {
        if (req == null || roomId == null) {
            throw new IllegalArgumentException("Message request or room ID cannot be null");
        }

        Room room = roomRepo.findByRoomId(roomId);
        if (room == null) {
            throw new RuntimeException("Room not found");
        }

        Message message = new Message();
        message.setContent(req.getContent());
        message.setSender(req.getSender());
        message.setTimeStamp(LocalDateTime.now());

        room.getMessages().add(message);
        roomRepo.save(room);

        return message;
    }
}