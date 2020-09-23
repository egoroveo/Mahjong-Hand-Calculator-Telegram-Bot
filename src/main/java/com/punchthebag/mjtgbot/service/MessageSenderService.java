package com.punchthebag.mjtgbot.service;

import com.punchthebag.mjtgbot.constant.TelegramConstants;
import com.punchthebag.mjtgbot.request.SendMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageSenderService {
    private Logger logger = LoggerFactory.getLogger(MessageSenderService.class);

    @Autowired
    public MessageSenderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${telegram_bot.key}")
    private String botKey;

    private RestTemplate restTemplate;

    public void sendMessage(String message, Integer chatId) {
        logger.info("Sending message " + message + " to chat " + chatId);

        final String uri = TelegramConstants.URL + botKey + TelegramConstants.SEND_MESSAGE_ADDRESS;
        String result = restTemplate.postForObject(uri, generateRequest(message, chatId), String.class);

        System.out.println(result);
    }

    private HttpEntity generateRequest(String message, Integer chatId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        SendMessageRequest request = new SendMessageRequest();
        request.setChat_id(chatId);
        request.setText(message);

        return new HttpEntity(request, headers);
    }
}
