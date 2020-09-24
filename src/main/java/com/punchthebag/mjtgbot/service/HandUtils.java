package com.punchthebag.mjtgbot.service;

import com.punchthebag.mjtgbot.constant.MahjongConstants;
import com.punchthebag.mjtgbot.entity.ActionForHand;
import com.punchthebag.mjtgbot.entity.Hand;
import com.punchthebag.mjtgbot.entity.PartialSetType;
import org.springframework.stereotype.Service;

@Service
public class HandUtils {

    //TODO: Implement
    public boolean hasTile(Hand hand, int rank, int suit) {
        throw new UnsupportedOperationException();
    }

    public boolean hasPair(Hand hand, int rank, int suit) {
        throw new UnsupportedOperationException();
    }

    public boolean hasSet(Hand hand, int rank, int suit) {
        throw new UnsupportedOperationException();
    }

    public boolean hasConsequentialSet(Hand hand, int startingRank, int suit) {
        throw new UnsupportedOperationException();
    }

    public boolean hasPartialSet(Hand hand, int rank, int suit, PartialSetType type) {
        throw new UnsupportedOperationException();
    }

    public void addTile(Hand hand, int rank, int suit) {
        throw new UnsupportedOperationException();
    }

    public void addPair(Hand hand, int rank, int suit) {
        throw new UnsupportedOperationException();
    }

    public void addSet(Hand hand, int rank, int suit) {
        throw new UnsupportedOperationException();
    }

    public void addConsequentialSet(Hand hand, int startingRank, int suit) {
        throw new UnsupportedOperationException();
    }

    public void addPartialSet(Hand hand, int startingRank, int suit, PartialSetType type) {
        throw new UnsupportedOperationException();
    }

    public void removeTile(Hand hand, int rank, int suit) {
        throw new UnsupportedOperationException();
    }

    public void removePair(Hand hand, int rank, int suit) {
        throw new UnsupportedOperationException();
    }

    public void removeSet(Hand hand, int rank, int suit) {
        throw new UnsupportedOperationException();
    }

    public void removeConsequentialSet(Hand hand, int startingRank, int suit) {
        throw new UnsupportedOperationException();
    }

    public void removePartialSet(Hand hand, int startingRank, int suit, PartialSetType type) {
        throw new UnsupportedOperationException();
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
