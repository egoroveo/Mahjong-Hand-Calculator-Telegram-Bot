package com.punchthebag.mjtgbot.request;

public record UpdateRequest(Integer update_id, Message message, InlineQuery inlineQuery) {}
