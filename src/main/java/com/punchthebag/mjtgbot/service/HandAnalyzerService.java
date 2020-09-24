package com.punchthebag.mjtgbot.service;

import com.punchthebag.mjtgbot.entity.Hand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HandAnalyzerService {

    private final Logger logger = LoggerFactory.getLogger(HandAnalyzerService.class);

    private final ShantenCalculator shantenCalculator;

    public HandAnalyzerService(ShantenCalculator shantenCalculator) {
        this.shantenCalculator = shantenCalculator;
    }

    public String analyze(Hand hand) {
        logger.info("Analyzing hand: " + hand);
        return "Hand: " + hand.getContent() + " Shanten: " + shantenCalculator.getShanten(hand);
    }
}
