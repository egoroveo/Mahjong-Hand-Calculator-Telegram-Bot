package com.punchthebag.mjtgbot.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SendMessageRequest(@JsonProperty("chat_id") Integer chatId,
                                 @JsonProperty("text") String text) {}
