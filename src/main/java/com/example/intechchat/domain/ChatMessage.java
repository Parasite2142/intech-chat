package com.example.intechchat.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor(staticName = "create")
public class ChatMessage {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    private final String userName;

    private final String messageBody;

    private final String messageTime = DATE_FORMAT.format(LocalDateTime.now());
}
