package com.offnine.chat_app_springboot.repo;

import com.offnine.chat_app_springboot.entites.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepo extends MongoRepository<Room,String> {
    // get  room using room id
    Room  findByRoomId(String roomId);
}
