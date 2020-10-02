package com.punchthebag.mjtgbot.entity;

public class Out {

    private Tile tile;
    private int amount;

    public Out(Tile tile) {
        this.tile = tile;
        this.amount = 0;
    }

    public Out(Tile tile, int amount) {
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
