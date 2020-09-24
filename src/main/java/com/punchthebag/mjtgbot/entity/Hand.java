package com.punchthebag.mjtgbot.entity;

import com.punchthebag.mjtgbot.service.HandParser;
import org.apache.commons.lang3.StringUtils;


public class Hand {

    public Hand(HandParser handParser) {
        this.handParser = handParser;
    }

    public void init(String content) {
        this.content = StringUtils.lowerCase(content);
        this.tiles = handParser.parseContent(content);
        this.valid = tiles != null;
    }

    private HandParser handParser;

    private String content;

    private int[] tiles;

    private boolean valid = false;

    public String getContent() {
        return content;
    }

    public int[] getTiles() {
        return tiles;
    }

    public boolean isValid() {
        return valid;
    }

}
