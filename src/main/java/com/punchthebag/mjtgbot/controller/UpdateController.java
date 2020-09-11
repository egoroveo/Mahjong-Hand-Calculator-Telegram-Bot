package com.punchthebag.mjtgbot.controller;

import com.punchthebag.mjtgbot.constant.TelegramConstants;
import com.punchthebag.mjtgbot.request.UpdateRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class UpdateController {


    @RequestMapping(value = TelegramConstants.WEBHOOK_ADDRESS, method = {RequestMethod.GET, RequestMethod.POST})
    public void update(@RequestBody UpdateRequest updateRequest) {
        System.out.println("req: " + updateRequest.getUpdate_id() + updateRequest.getMessage().getMessage_id() + updateRequest.getMessage().getText());
    }

}
