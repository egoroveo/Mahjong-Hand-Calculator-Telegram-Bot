package com.punchthebag.mjtgbot.controller;

import com.punchthebag.mjtgbot.constant.TelegramConstants;
import com.punchthebag.mjtgbot.entity.AnalysisResult;
import com.punchthebag.mjtgbot.request.UpdateRequest;
import com.punchthebag.mjtgbot.service.HandAnalyzerService;
import com.punchthebag.mjtgbot.service.MessageSenderService;
import com.punchthebag.mjtgbot.view.FixedMessagesProvider;
import com.punchthebag.mjtgbot.view.MessageGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UpdateController {

    private final Logger logger = LoggerFactory.getLogger(UpdateController.class);

    private final HandAnalyzerService handAnalyzerService;
    private final MessageSenderService messageSenderService;
    private final MessageGenerator messageGenerator;
    private final FixedMessagesProvider fixedMessagesProvider;

    @Autowired
    public UpdateController(HandAnalyzerService handAnalyzerService,
                            MessageSenderService messageSenderService,
                            MessageGenerator messageGenerator,
                            FixedMessagesProvider fixedMessagesProvider) {
        this.handAnalyzerService = handAnalyzerService;
        this.messageSenderService = messageSenderService;
        this.messageGenerator = messageGenerator;
        this.fixedMessagesProvider = fixedMessagesProvider;
    }

    @RequestMapping(value = TelegramConstants.WEBHOOK_ADDRESS, method = {RequestMethod.GET, RequestMethod.POST})
    public void update(@RequestBody UpdateRequest updateRequest) {
        logger.info("Starting update: " + updateRequest.toString());
        try {
            boolean isInline = updateRequest.inlineQuery() != null;
            String query;
            String id;
            if (isInline) {
                query = updateRequest.inlineQuery().query();
                id = updateRequest.inlineQuery().id();
            } else {
                query = updateRequest.message().text();
                id = updateRequest.message().chat().id().toString();
            }

            if (isCommand(query)) {
                messageSenderService.sendMessage(fixedMessagesProvider.getMessageForCommand(query), id, isInline, null);
            } else {
                AnalysisResult result = handAnalyzerService.analyze(query);

                String inlineTitle = fixedMessagesProvider.generateTitle(result != null);

                String response;
                if (result != null) {
                    response = messageGenerator.generateResponse(result);
                } else {
                    response = fixedMessagesProvider.generateErrorResponse();
                }

                messageSenderService.sendMessage(response, id, isInline, inlineTitle);

            }

        } catch (Exception e) {
            logger.warn(e.toString());
        }
        logger.info("Finishing update: " + updateRequest.toString());
    }


    private boolean isCommand(String query) {
        if (query == null) {
            return false;
        }
        return query.startsWith(TelegramConstants.COMMAND_PREFIX);
    }

}
