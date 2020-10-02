package com.punchthebag.mjtgbot.request;

public class Message {
    private Integer message_id;
    private String text;
    private Chat chat;

    public Integer getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getName())
                .append("@")
                .append(Integer.toHexString(hashCode()))
                .append(" message_id: ")
                .append(message_id)
                .append(" text: ")
                .append(text)
                .append(" chat: ")
                .append(chat);
        return stringBuilder.toString();
    }
}
