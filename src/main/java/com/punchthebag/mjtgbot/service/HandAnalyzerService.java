package com.punchthebag.mjtgbot.service;

import com.punchthebag.mjtgbot.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

@Service
public class HandAnalyzerService {

    private final Logger logger = LoggerFactory.getLogger(HandAnalyzerService.class);

    private final ShantenCalculator shantenCalculator;
    private final HandActionsService handActionsService;

    public HandAnalyzerService(ShantenCalculator shantenCalculator, HandActionsService handActionsService) {
        this.shantenCalculator = shantenCalculator;
        this.handActionsService = handActionsService;
    }

    @Lookup
    public Hand getHand() {
        return null;
    }

    public AnalysisResult analyze(String handText) {
        logger.info("Analyzing hand: " + handText);
        Hand hand = getHand().init(handText);
        if (!hand.isValid()) {
            return null;
        }

        AnalysisResult result = new AnalysisResult(hand);
        int shanten = shantenCalculator.getShanten(hand);
        result.setShanten(shanten);

        calculateOuts(hand, shanten, result);

        return result;
    }

    private void calculateOuts(Hand hand, int shanten, AnalysisResult result) {

        handActionsService.forAllTiles(((rank, suit) -> {
            if (hand.hasTile(rank, suit)) {
                hand.removeTile(rank, suit);
                if (shanten == shantenCalculator.getShanten(hand)) {
                    final DiscardOption discardOption = new DiscardOption(new Tile(rank, Suit.getSuit(suit)));
                    handActionsService.forAllTiles(((newRank, newSuit) -> {
                        hand.addTile(newRank, newSuit);
                        if (shanten > shantenCalculator.getShanten(hand)) {
                            Tile tile = new Tile(newRank + 1, Suit.getSuit(newSuit));
                            discardOption.addOutForTile(tile);
                        }
                        hand.removeTile(newRank, newSuit);
                    }));
                    result.getDiscardOptions().add(discardOption);
                }
                hand.addTile(rank, suit);
            }
        }));
    }


}
