package com.punchthebag.mjtgbot.entity;

import com.punchthebag.mjtgbot.constant.MahjongConstants;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Collectors;

public class Hand {

    public Hand(String content) {
        this.content = StringUtils.lowerCase(content);
        this.tiles = new int[MahjongConstants.SUIT_COUNT * MahjongConstants.RANKS_COUNT + MahjongConstants.HONOR_RANKS_COUNT];
        parseContent();
    }

    private String content;

    private int[] tiles;

    private boolean valid;

    public String getContent() {
        return content;
    }

    public int[] getTiles() {
        return tiles;
    }

    public boolean isValid() {
        return valid;
    }

    private void parseContent() {
        char[] chars = content.chars()
                .mapToObj(c -> Character.toString((char) c))
                .collect(Collectors.joining())
                .toCharArray();
        int suit = -1;
        int tileCount = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (MahjongConstants.SUITS.contains(chars[i])) {
                suit = MahjongConstants.SUIT_NUMBERS.get(chars[i]);
            } else if (Character.isDigit(chars[i])) {
                int rank = chars[i] - '0';
                if (!isSuitAndRankValid(suit, rank)) {
                    valid = false;
                    return;
                }
                tiles[rank + MahjongConstants.RANKS_COUNT * suit]++;
                tileCount++;
            } else {
                valid = false;
                return;
            }
        }
        valid = tileCount == MahjongConstants.HAND_TILE_COUNT;
    }

    private boolean isSuitAndRankValid(int suit, int rank) {
        if (suit == -1) {
            return false;
        }
        if (suit == MahjongConstants.HONOR_SUIT && rank > 7) {
            return false;
        }
        return true;
    }

}
