package com.punchthebag.mjtgbot.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public record InputMessageContent(@JsonProperty("message_text") String messageText) {}
