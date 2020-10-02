package com.punchthebag.mjtgbot.entity;

import java.util.LinkedList;
import java.util.List;

public class AnalysisResult {

    private Hand hand;
    private List<DiscardOption> discardOptions;
    private int shanten;

    public AnalysisResult(Hand hand) {
        this.hand = hand;
        this.discardOptions = new LinkedList<>();
    }

    public Hand getHand() {
        return hand;
    }

    public int getShanten() {
        return shanten;
    }

    public void setShanten(int shanten) {
        this.shanten = shanten;
    }


    public List<DiscardOption> getDiscardOptions() {
        return discardOptions;
    }
}
