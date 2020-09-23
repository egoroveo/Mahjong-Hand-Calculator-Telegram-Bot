package com.punchthebag.mjtgbot.controller;

import com.punchthebag.mjtgbot.constant.TelegramConstants;
import com.punchthebag.mjtgbot.entity.Hand;
import com.punchthebag.mjtgbot.request.UpdateRequest;
import com.punchthebag.mjtgbot.service.HandAnalyzerService;
import com.punchthebag.mjtgbot.service.MessageSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UpdateController {

    private Logger logger = LoggerFactory.getLogger(UpdateController.class);

    private HandAnalyzerService handAnalyzerService;
    private MessageSenderService messageSenderService;

    @Autowired
    public UpdateController(HandAnalyzerService handAnalyzerService, MessageSenderService messageSenderService) {
        this.handAnalyzerService = handAnalyzerService;
        this.messageSenderService = messageSenderService;
    }

    @RequestMapping(value = TelegramConstants.WEBHOOK_ADDRESS, method = {RequestMethod.GET, RequestMethod.POST})
    public void update(@RequestBody UpdateRequest updateRequest) {
        logger.info("Starting update: " + updateRequest.toString());
        //TODO: Check if several messages can be in one request
        Hand hand = new Hand(updateRequest.getMessage().getText());
        String response = handAnalyzerService.analyze(hand);
        messageSenderService.sendMessage(response, updateRequest.getMessage().getChat().getId());
        logger.info("Finishing update: " + updateRequest.toString());
    }

}
