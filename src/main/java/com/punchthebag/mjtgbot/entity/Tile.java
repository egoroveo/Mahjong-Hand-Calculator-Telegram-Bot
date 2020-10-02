package com.punchthebag.mjtgbot.entity;

import java.util.Objects;

public class Tile {

    private int rank;
    private Suit suit;

    public Tile(int rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) o;
        return rank == tile.rank &&
                suit == tile.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

}
