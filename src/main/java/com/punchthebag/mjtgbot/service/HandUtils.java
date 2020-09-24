package com.punchthebag.mjtgbot.service;

import com.punchthebag.mjtgbot.constant.MahjongConstants;
import com.punchthebag.mjtgbot.entity.ActionForHand;
import com.punchthebag.mjtgbot.entity.Hand;
import com.punchthebag.mjtgbot.entity.PatternType;
import org.springframework.stereotype.Service;

@Service
public class HandUtils {

    public boolean hasTile(Hand hand, int rank, int suit) {
        return hasSet(hand, rank, suit, PatternType.ONE);
    }

    public boolean hasPair(Hand hand, int rank, int suit) {
        return hasSet(hand, rank, suit, PatternType.PAIR);
    }

    public void addTile(Hand hand, int rank, int suit) {
        addSet(hand, rank, suit, PatternType.ONE);
    }

    public void addPair(Hand hand, int rank, int suit) {
        addSet(hand, rank, suit, PatternType.PAIR);
    }

    public void removeTile(Hand hand, int rank, int suit) {
        removeSet(hand, rank, suit, PatternType.ONE);
    }

    public void removePair(Hand hand, int rank, int suit) {
        removeSet(hand, rank, suit, PatternType.PAIR);
    }

    public boolean hasSet(Hand hand, int rank, int suit, PatternType type) {
        if (rank + type.getPattern().length > MahjongConstants.RANKS_COUNT
                || (suit == MahjongConstants.HONOR_SUIT && type.getPattern().length > 1)) {
            return false;
        }
        for (int i = 0; i < type.getPattern().length; i++) {
            if (hand.getTiles()[suit * MahjongConstants.RANKS_COUNT + rank + i] < type.getPattern()[i]) {
                return false;
            }
        }
        return true;
    }

    public void addSet(Hand hand, int rank, int suit, PatternType type) {
        for (int i = 0; i < type.getPattern().length; i++) {
            hand.getTiles()[suit * MahjongConstants.RANKS_COUNT + rank + i] += type.getPattern()[i];
        }
    }

    public void removeSet(Hand hand, int rank, int suit, PatternType type) {
        for (int i = 0; i < type.getPattern().length; i++) {
            hand.getTiles()[suit * MahjongConstants.RANKS_COUNT + rank + i] -= type.getPattern()[i];
        }
    }

    public boolean isOrphan(int rank, int suit) {
        return suit == MahjongConstants.HONOR_SUIT
                || rank == MahjongConstants.FIRST_TERMINAL
                || rank == MahjongConstants.LAST_TERMINAL;
    }

    public void forAllTiles(Hand hand, ActionForHand action) {
        // number tiles
        for (int suit = 0; suit < MahjongConstants.SUIT_COUNT; suit++) {
            for (int rank = 0; rank < MahjongConstants.RANKS_COUNT; rank++) {
                action.apply(rank, suit);
            }
        }

        // honor tiles
        for (int rank = 0; rank < MahjongConstants.HONOR_RANKS_COUNT; rank++) {
            action.apply(rank, MahjongConstants.HONOR_SUIT);
        }
    }

}
