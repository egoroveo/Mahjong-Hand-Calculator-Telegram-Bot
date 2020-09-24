package com.punchthebag.mjtgbot.entity;

import com.punchthebag.mjtgbot.constant.MahjongConstants;
import org.apache.commons.lang3.StringUtils;

public class Hand {

    public Hand(String content) {
        this.content = StringUtils.lowerCase(content);
        this.tiles = new int[MahjongConstants.SUIT_COUNT * MahjongConstants.RANKS_COUNT + MahjongConstants.HONOR_RANKS_COUNT];
        parseContent();
    }

    private String content;

    public String getContent() {
        return content;
    }

    private int[] tiles;

    public int[] getTiles() {
        return tiles;
    }

    private void parseContent() {
        int lastIndex = -1;
        for (int i = 0; i < MahjongConstants.SUIT_COUNT + 1; i++) {
            int currentIndex = content.indexOf(MahjongConstants.SUITS.get(i));
            if (currentIndex != -1) {
                addSuit(content.substring(lastIndex + 1, currentIndex), i);
                lastIndex = currentIndex;
            }
        }
    }

    private void addSuit(String tileNumbers, Integer suit) {
        tileNumbers.chars().forEach(value -> {
            int rank = value - Character.valueOf('0');
            tiles[rank + MahjongConstants.RANKS_COUNT * suit]++;
        });
    }
}
