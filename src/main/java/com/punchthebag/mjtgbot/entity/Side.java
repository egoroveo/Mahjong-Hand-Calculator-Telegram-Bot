package com.punchthebag.mjtgbot.entity;

public class Side {

    private Tile tile;
    private int amount;

    public Side(Tile tile) {
        this.tile = tile;
        this.amount = 1;
    }

    public Side(Tile tile, int amount) {
        this.tile = tile;
        this.amount = amount;
    }

    public void increment() {
        amount++;
    }

    public Tile getTile() {
        return tile;
    }

    public int getAmount() {
        return amount;
    }
}
