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
        stringBuilder.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        stringBuilder.append(" message_id: ");
        stringBuilder.append(message_id);
        stringBuilder.append(" text: ");
        stringBuilder.append(text);
        stringBuilder.append(" chat: ");
        stringBuilder.append(chat);
        return stringBuilder.toString();
    }
}
