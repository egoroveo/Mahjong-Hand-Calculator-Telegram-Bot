package com.punchthebag.mjtgbot.entity;

import com.punchthebag.mjtgbot.constant.MahjongConstants;

public class Tile {

    private final int rank;
    private final Suit suit;

    private static Tile[][] values = new Tile[MahjongConstants.RANKS_COUNT][MahjongConstants.SUIT_COUNT + 1];
    static {
        for (int i = 0; i < MahjongConstants.RANKS_COUNT; i++) {
            for (int j = 0; j < MahjongConstants.SUIT_COUNT + 1; j++) {
                values[i][j] = new Tile(i, Suit.getSuit(j));
            }
        }
    }

    private Tile(int rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public static Tile of(int rank, int suit) {
        return values[rank][suit];
    }

    public static Tile of(int rank, Suit suit) {
        return values[rank][suit.getNumber()];
    }

    public int getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

}
