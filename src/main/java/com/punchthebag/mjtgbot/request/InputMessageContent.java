package com.punchthebag.mjtgbot.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public record InputMessageContent(String message_text) {}
