package com.punchthebag.mjtgbot.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateRequest(@JsonProperty("update_id") Integer updateId,
                            @JsonProperty("message") Message message,
                            @JsonProperty("inline_query") InlineQuery inlineQuery) {}
