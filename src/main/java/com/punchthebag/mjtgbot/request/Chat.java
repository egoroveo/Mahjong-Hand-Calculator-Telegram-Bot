package com.punchthebag.mjtgbot.request;

public class Chat {
    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getName())
                .append("@")
                .append(Integer.toHexString(hashCode()))
                .append(" id: ")
                .append(id);
        return stringBuilder.toString();
    }
}
