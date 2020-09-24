package com.punchthebag.mjtgbot.entity;

import java.util.Map;

public class Hand {

    //TODO: fill tiles
    public Hand(String content) {
        this.content = content;
    }

    private String content;

    public String getContent() {
        return content;
    }

    private int[] tiles;

    public int[] getTiles() {
        return tiles;
    }
}
