package com.punchthebag.mjtgbot.service;

import com.punchthebag.mjtgbot.entity.Hand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

@Service
public class HandAnalyzerService {

    private final Logger logger = LoggerFactory.getLogger(HandAnalyzerService.class);

    private final ShantenCalculator shantenCalculator;
    private final HandUtils handUtils;

    public HandAnalyzerService(ShantenCalculator shantenCalculator, HandUtils handUtils) {
        this.shantenCalculator = shantenCalculator;
        this.handUtils = handUtils;
    }

    @Lookup
    public Hand getHand() {
        return null;
    }

    public String analyze(String handText) {
        logger.info("Analyzing hand: " + handText);
        Hand hand = getHand();
        hand.init(handText);
        if (!hand.isValid()) {
            return null;
        }

        final StringBuilder response = new StringBuilder();
        response.append("Hand: " + hand.getContent() + " Shanten: " + shantenCalculator.getShanten(hand) + System.lineSeparator());

        return response.toString();
    }
}
