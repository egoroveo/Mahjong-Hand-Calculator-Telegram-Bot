package com.punchthebag.mjtgbot.entity;

import com.punchthebag.mjtgbot.constant.MahjongConstants;
import com.punchthebag.mjtgbot.service.HandParser;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class Hand {

    public Hand(HandParser handParser) {
        this.handParser = handParser;
    }

    public Hand init(String content) {
        this.content = StringUtils.lowerCase(content);
        this.value = handParser.parseContent(content);
        this.initialValue = Arrays.copyOf(value, value.length);
        this.valid = value != null;
        return this;
    }

    private HandParser handParser;

    private String content;

    private int[] value;

    private int[] initialValue;

    private boolean valid = false;

    public String getContent() {
        return content;
    }

    public boolean isValid() {
        return valid;
    }

    public List<Tile> getTiles() {
        List<Tile> result = new LinkedList<>();
        for (int tileType = 0; tileType < value.length; tileType++) {
            for (int tileCount = 0; tileCount < value[tileType]; tileCount++) {
                Tile tile = Tile.of(tileType % MahjongConstants.RANKS_COUNT,
                        tileType / MahjongConstants.RANKS_COUNT);
                result.add(tile);
            }
        }
        return result;
    }

    public boolean hasTile(int rank, int suit) {
        return hasSet(rank, suit, PatternType.ONE);
    }

    public boolean hasPair(int rank, int suit) {
        return hasSet(rank, suit, PatternType.PAIR);
    }

    public void addTile(int rank, int suit) {
        addSet(rank, suit, PatternType.ONE);
    }

    public void addPair(int rank, int suit) {
        addSet(rank, suit, PatternType.PAIR);
    }

    public void removeTile(int rank, int suit) {
        removeSet(rank, suit, PatternType.ONE);
    }

    public void removePair(int rank, int suit) {
        removeSet(rank, suit, PatternType.PAIR);
    }

    public boolean hasSet(int rank, int suit, PatternType type) {
        if (rank + type.getPattern().length > MahjongConstants.RANKS_COUNT
                || (suit == MahjongConstants.HONOR_SUIT && type.getPattern().length > 1)) {
            return false;
        }
        for (int i = 0; i < type.getPattern().length; i++) {
            if (value[position(rank, suit) + i] < type.getPattern()[i]) {
                return false;
            }
        }
        return true;
    }

    public void addSet(int rank, int suit, PatternType type) {
        for (int i = 0; i < type.getPattern().length; i++) {
            value[position(rank, suit) + i] += type.getPattern()[i];
        }
    }

    public void removeSet(int rank, int suit, PatternType type) {
        for (int i = 0; i < type.getPattern().length; i++) {
            value[position(rank, suit) + i] -= type.getPattern()[i];
        }
    }

    private int position(int rank, int suit) {
        return suit * MahjongConstants.RANKS_COUNT + rank;
    }

    public int initialTileCount(Tile tile) {
        return initialValue[position(tile.getRank(), tile.getSuit().getNumber())];
    }

}
