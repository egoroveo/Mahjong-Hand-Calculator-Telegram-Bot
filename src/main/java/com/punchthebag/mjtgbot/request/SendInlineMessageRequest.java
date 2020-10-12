package com.punchthebag.mjtgbot.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SendInlineMessageRequest(@JsonProperty("inline_query_id") String inlineQueryId,
                                       @JsonProperty("results") List<InlineQueryResult> results) {}
