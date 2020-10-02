package com.punchthebag.mjtgbot.service;

import com.punchthebag.mjtgbot.constant.MahjongConstants;
import com.punchthebag.mjtgbot.entity.ActionForHand;
import org.springframework.stereotype.Service;

@Service
public class HandActionsService {

    public boolean isOrphan(int rank, int suit) {
        return suit == MahjongConstants.HONOR_SUIT
                || rank == MahjongConstants.FIRST_TERMINAL
                || rank == MahjongConstants.LAST_TERMINAL;
    }

    public void forAllTiles(ActionForHand action) {
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

    public int position(int rank, int suit) {
        return suit * MahjongConstants.RANKS_COUNT + rank;
    }

}
