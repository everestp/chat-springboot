package com.offnine.chat_app_springboot.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {
    private String content;
    private String sender;
    private String roomId;

    private LocalDateTime messageTime;
}
