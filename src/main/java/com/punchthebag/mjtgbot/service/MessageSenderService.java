package com.punchthebag.mjtgbot.service;

import com.punchthebag.mjtgbot.constant.TelegramConstants;
import com.punchthebag.mjtgbot.request.InlineQueryResult;
import com.punchthebag.mjtgbot.request.InputMessageContent;
import com.punchthebag.mjtgbot.request.SendInlineMessageRequest;
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

import java.util.List;

@Service
public class MessageSenderService {
    private final Logger logger = LoggerFactory.getLogger(MessageSenderService.class);

    @Autowired
    public MessageSenderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${telegram_bot.key}")
    private String botKey;

    @Value("${telegram_bot.server}")
    private String botServer;

    private RestTemplate restTemplate;

    public void sendMessage(String message, String id, boolean isInline, String inlineTitle, boolean isSuccess) {
        logger.info("Sending message " + message + " to chat " + id);

        final String uri = TelegramConstants.URL
                + botKey
                + (isInline ? TelegramConstants.SEND_INLINE_MESSAGE_ADDRESS : TelegramConstants.SEND_MESSAGE_ADDRESS);
        HttpEntity request = isInline ? generateInlineRequest(message, id, inlineTitle, isSuccess) : generateRequest(message, Integer.valueOf(id));
        logger.info("Request: " + request);
        String result = restTemplate.postForObject(uri, request, String.class);

        System.out.println(result);
    }

    private HttpEntity<SendInlineMessageRequest> generateInlineRequest(String message, String queryId, String inlineTitle, boolean isSuccess) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        InputMessageContent inputMessageContent = new InputMessageContent(message);
        InlineQueryResult inlineQueryResult = new InlineQueryResult(
                "article",
                queryId,
                inlineTitle,
                inputMessageContent,
                botServer + TelegramConstants.IMAGE_FOLDER
                        + (isSuccess ? TelegramConstants.THUMB_IMAGE : TelegramConstants.THUMB_ERROR_IMAGE),
                TelegramConstants.THUMB_WIDTH,
                TelegramConstants.THUMB_HEIGHT
        );
        SendInlineMessageRequest request = new SendInlineMessageRequest(
                queryId,
                List.of(inlineQueryResult)
        );

        return new HttpEntity<>(request, headers);
    }

    private HttpEntity<SendMessageRequest> generateRequest(String message, Integer chatId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        SendMessageRequest request = new SendMessageRequest(chatId, message);

        return new HttpEntity<>(request, headers);
    }
}
