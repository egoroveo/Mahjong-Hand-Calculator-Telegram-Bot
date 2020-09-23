package com.punchthebag.mjtgbot.request;

public class SendMessageRequest {

    public Integer getChat_id() {
        return chat_id;
    }

    public void setChat_id(Integer chat_id) {
        this.chat_id = chat_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private Integer chat_id;
    private String text;

}
