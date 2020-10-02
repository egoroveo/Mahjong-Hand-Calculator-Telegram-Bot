package com.punchthebag.mjtgbot.entity;

import java.util.LinkedList;
import java.util.List;

public class DiscardOption {

    private Tile tile;
    private List<Out> outs;

    public DiscardOption(Tile tile) {
        this.tile = tile;
        this.outs = new LinkedList<>();
    }

    public Tile getTile() {
        return tile;
    }

    public List<Out> getOuts() {
        return outs;
    }

    public void addOutForTile(Tile tile) {
        boolean hasTile = false;
        for (Out out : getOuts()) {
            if (out.getTile().equals(tile)) {
                out.increment();
                hasTile = true;
                break;
            }
        }
        if (!hasTile) {
            outs.add(new Out(tile));
        }
    }
}
