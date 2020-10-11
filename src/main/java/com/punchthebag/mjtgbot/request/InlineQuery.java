package com.punchthebag.mjtgbot.request;

public record InlineQuery(User user, String query, String id) {}
