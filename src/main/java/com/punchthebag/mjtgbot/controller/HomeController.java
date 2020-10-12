package com.punchthebag.mjtgbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public @ResponseBody
    ResponseEntity<String> home() {
        logger.info("Home page");
        return new ResponseEntity<>("Check out our telegram bot <a href=\"https://t.me/RiichiTrainerBot\">@RiichiTrainerBot</a>", HttpStatus.OK);
    }
}
