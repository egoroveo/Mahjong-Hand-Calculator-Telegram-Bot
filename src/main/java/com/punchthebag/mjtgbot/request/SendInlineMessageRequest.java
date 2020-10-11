package com.punchthebag.mjtgbot.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect
public record SendInlineMessageRequest(String inline_query_id,
                                       List<InlineQueryResult> results) {}
