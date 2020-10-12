package com.punchthebag.mjtgbot.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record InlineQuery(@JsonProperty("user") User user,
                          @JsonProperty("query") String query,
                          @JsonProperty("id") String id) {}
