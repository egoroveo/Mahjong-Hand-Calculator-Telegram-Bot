package com.punchthebag.mjtgbot.service;

import com.punchthebag.mjtgbot.constant.MahjongConstants;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class HandParser {

    public int[] parseContent(String content) {
        int[] tiles = new int[MahjongConstants.SUIT_COUNT * MahjongConstants.RANKS_COUNT + MahjongConstants.HONOR_RANKS_COUNT];
        char[] chars = generateCharArray(content);
        int suit = -1;
        int tileCount = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (MahjongConstants.SUITS.contains(chars[i])) {
                suit = MahjongConstants.SUIT_NUMBERS.get(chars[i]);
            } else if (Character.isDigit(chars[i])) {
                int rank = chars[i] - '0';
                if (!isSuitAndRankValid(suit, rank)) {
                    return null;
                }
                tiles[rank + MahjongConstants.RANKS_COUNT * suit]++;
                tileCount++;
            } else {
                return null;
            }
        }
        if (tileCount != MahjongConstants.HAND_TILE_COUNT) {
            return null;
        }
        return tiles;
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

    private char[] generateCharArray(String content) {
        return content.chars()
                .mapToObj(c -> Character.toString((char) c))
                .collect(Collectors.joining())
                .toCharArray();
    }
}
