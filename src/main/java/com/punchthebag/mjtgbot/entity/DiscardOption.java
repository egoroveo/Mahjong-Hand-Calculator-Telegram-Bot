package com.punchthebag.mjtgbot.entity;

import java.util.LinkedList;
import java.util.List;

public class DiscardOption {

    private Tile tile;
    private List<Side> outs;

    public DiscardOption(Tile tile) {
        this.tile = tile;
        this.outs = new LinkedList<>();
    }

    public Tile getTile() {
        return tile;
    }

    public List<Side> getOuts() {
        return outs;
    }

    public int getOutsCount() {
        int count = 0;
        for (Side out : outs) {
            count += out.getAmount();
        }
        return count;
    }

    public void addOutsForTile(Tile tile, int count) {
        for (int i = 0; i < count; i++) {
            addOutForTile(tile);
        }
    }

    public void addOutForTile(Tile tile) {
        boolean hasTile = false;
        for (Side out : getOuts()) {
            if (out.getTile().equals(tile)) {
                out.increment();
                hasTile = true;
                break;
            }
        }
        if (!hasTile) {
            outs.add(new Side(tile));
        }
    }
}
