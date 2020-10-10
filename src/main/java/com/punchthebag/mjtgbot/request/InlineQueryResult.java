package com.punchthebag.mjtgbot.request;

public class InlineQueryResult {

    String type;
    String id;
    String title;
    InputMessageContent input_message_content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public InputMessageContent getInput_message_content() {
        return input_message_content;
    }

    public void setInput_message_content(InputMessageContent input_message_content) {
        this.input_message_content = input_message_content;
    }
}
