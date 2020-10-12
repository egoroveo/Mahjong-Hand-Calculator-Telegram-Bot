package com.punchthebag.mjtgbot.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Chat(@JsonProperty("id") Integer id,
                   @JsonProperty("username") String username) {}
