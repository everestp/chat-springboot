package com.offnine.chat_app_springboot.controller;

import java.time.LocalDateTime;

import com.offnine.chat_app_springboot.Request.MessageRequest;
import com.offnine.chat_app_springboot.config.Appconstants;
import com.offnine.chat_app_springboot.entites.Message;
import com.offnine.chat_app_springboot.entites.Room;
import com.offnine.chat_app_springboot.repo.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin(Appconstants.FRONTEND_BASE_URL)

public class ChatController {




    @Autowired
    private RoomRepo roomRepo;



    // for sending and receivintg messsage
    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")  // subscibe the message by ckient
    public Message sendMessage(
            @RequestBody MessageRequest req,
            @DestinationVariable String  roomId

    ){

        Room room =roomRepo.findByRoomId(roomId);
        Message message = new Message();
        message.setContent(req.getContent());
        message.setSender(req.getSender());
        message.setTimeStamp(LocalDateTime.now());
        if(room != null){
            room.getMessages().add(message);
            roomRepo.save(room);
        }
        else{
            throw new RuntimeException("Room not found");
        }

        return message;



    }



}
