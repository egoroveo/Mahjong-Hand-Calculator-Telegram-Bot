package com.punchthebag.mjtgbot.service;

import com.punchthebag.mjtgbot.constant.MahjongConstants;
import com.punchthebag.mjtgbot.entity.Suit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class HandParser {

    Logger logger = LoggerFactory.getLogger(HandParser.class);

    public int[] parseContent(String content) {
        int[] tiles = new int[MahjongConstants.SUIT_COUNT * MahjongConstants.RANKS_COUNT + MahjongConstants.HONOR_RANKS_COUNT];
        try {
            char[] chars = generateCharArray(content);
            int suit = -1;
            int tileCount = 0;
            for (int i = chars.length - 1; i >= 0; i--) {
                if (Suit.SUITS.contains(chars[i])) {
                    suit = Suit.getNumberByLetter(chars[i]);
                } else if (Character.isDigit(chars[i])) {
                    int rank = chars[i] - '0' - 1;
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
        } catch (RuntimeException e) {
            logger.warn("Parsing failed for content " + content);
            return null;
        }
        return tiles;
    }

    private boolean isSuitAndRankValid(int suit, int rank) {
        if (suit == -1) {
            return false;
        }
        return suit != MahjongConstants.HONOR_SUIT || rank <= 7;
    }

    private char[] generateCharArray(String content) {
        return content.chars()
                .mapToObj(c -> Character.toString((char) c))
                .collect(Collectors.joining())
                .toCharArray();
    }

}
