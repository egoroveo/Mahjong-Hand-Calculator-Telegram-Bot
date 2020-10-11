package com.punchthebag.mjtgbot.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public record InlineQueryResult(String type,
                                String id,
                                String title,
                                InputMessageContent input_message_content) {}
