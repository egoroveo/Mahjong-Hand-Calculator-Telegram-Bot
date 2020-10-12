package com.punchthebag.mjtgbot.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Message(@JsonProperty("message_id") Integer messageId,
                      @JsonProperty("text") String text,
                      @JsonProperty("chat") Chat chat) {}
