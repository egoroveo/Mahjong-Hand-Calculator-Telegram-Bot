package com.punchthebag.mjtgbot.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SendMessageRequest(@JsonProperty("chat_id") Integer chat_id, @JsonProperty("text") String text) {}
