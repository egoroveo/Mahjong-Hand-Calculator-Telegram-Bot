package com.punchthebag.mjtgbot.constant;

public class TelegramConstants {
    private TelegramConstants() {};
    public static final String WEBHOOK_ADDRESS = "/update";
    public static final String SEND_MESSAGE_ADDRESS = "/sendMessage";
    public static final String SEND_INLINE_MESSAGE_ADDRESS = "/answerInlineQuery";
    public static final String URL = "https://api.telegram.org/bot";
    public static final String COMMAND_PREFIX = "/";
    public static final String IMAGE_FOLDER = "/resources/images/";
    public static final String THUMB_IMAGE = "pin.png";

    public static final Integer THUMB_WIDTH = 134;
    public static final Integer THUMB_HEIGHT = 134;
}
