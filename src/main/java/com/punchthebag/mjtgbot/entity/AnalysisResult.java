package com.punchthebag.mjtgbot.entity;

import java.util.LinkedList;
import java.util.List;

public class AnalysisResult {

    private List<Tile> tiles;
    private List<DiscardOption> discardOptions;
    private int shanten;

    public AnalysisResult(Hand hand) {
        this.tiles = hand.getTiles();
        this.discardOptions = new LinkedList<>();
    }

    public int getShanten() {
        return shanten;
    }

    public void setShanten(int shanten) {
        this.shanten = shanten;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public List<DiscardOption> getDiscardOptions() {
        return discardOptions;
    }
}
