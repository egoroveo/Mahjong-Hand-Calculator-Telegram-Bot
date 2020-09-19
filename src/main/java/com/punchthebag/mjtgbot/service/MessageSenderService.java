package com.punchthebag.mjtgbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderService {
    private Logger logger = LoggerFactory.getLogger(MessageSenderService.class);

    // TODO: Implement
    public void sendMessage(String message, Integer chatId) {
        logger.debug("Sending message " + message + " to chat " + chatId);
    }
}
