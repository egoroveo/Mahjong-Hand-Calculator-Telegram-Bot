package com.punchthebag.mjtgbot.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record InlineQueryResult(@JsonProperty("type") String type,
                                @JsonProperty("id") String id,
                                @JsonProperty("title") String title,
                                @JsonProperty("input_message_content") InputMessageContent inputMessageContent,
                                @JsonProperty("thumb_url") String thumbUrl,
                                @JsonProperty("thumb_width") Integer thumbWidth,
                                @JsonProperty("thumb_height") Integer thumbHeight) {}
