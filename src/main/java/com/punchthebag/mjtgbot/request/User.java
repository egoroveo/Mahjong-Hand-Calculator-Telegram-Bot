package com.punchthebag.mjtgbot.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record User(@JsonProperty("id") Integer id) {}
