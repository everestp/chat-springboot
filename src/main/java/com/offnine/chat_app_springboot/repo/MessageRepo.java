package com.offnine.chat_app_springboot.repo;

import com.offnine.chat_app_springboot.entites.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepo extends MongoRepository<Message,String> {
}
