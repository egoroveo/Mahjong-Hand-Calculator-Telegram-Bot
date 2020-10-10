package com.punchthebag.mjtgbot.service;

import com.punchthebag.mjtgbot.constant.MahjongConstants;
import com.punchthebag.mjtgbot.entity.Hand;
import com.punchthebag.mjtgbot.entity.PatternType;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableInt;
import org.springframework.stereotype.Service;


@Service
public class ShantenCalculator {

    private final HandActionsService handActionsService;

    public ShantenCalculator(HandActionsService handActionsService) {
        this.handActionsService = handActionsService;
    }

    public Integer getShanten(Hand hand) {
        return Math.min(Math.min(getStandardShanten(hand), getPairsShanten(hand)), getOrphansShanten(hand));
    }

    // tiles left for the win by getting 7 pairs
    private Integer getPairsShanten(Hand hand) {
        final MutableInt shanten = new MutableInt(MahjongConstants.PAIRS_COUNT_TO_WIN - 1);
        handActionsService.forAllTiles((rank, suit) -> {
            if (hand.hasPair(rank, suit)) {
                shanten.decrement();
            }
        });
        return shanten.getValue();
    }

    // tiles left for the win by getting 13 orphans + pair
    private Integer getOrphansShanten(Hand hand) {

        final MutableInt shanten = new MutableInt(MahjongConstants.ORPHANS_COUNT);

        handActionsService.forAllTiles(((rank, suit) -> {
            if (handActionsService.isOrphan(rank, suit) && hand.hasTile(rank, suit)) {
                shanten.decrement();
            }
        }));

        if (hasOrphanPair(hand)) {
            shanten.decrement();
        }

        return shanten.intValue();
    }

    private boolean hasOrphanPair(Hand hand) {
        MutableBoolean hasPair = new MutableBoolean(false);
        handActionsService.forAllTiles(((rank, suit) -> {
            if (handActionsService.isOrphan(rank, suit) && hand.hasPair(rank, suit)) {
                hasPair.setTrue();
            }
        }));
        return hasPair.booleanValue();
    }

    // tiles left for the win by getting 4 sets + 1 pair
    private Integer getStandardShanten(Hand hand) {
        // taking a pair and recursively calculate shantens
        final MutableInt minShanten = new MutableInt(MahjongConstants.MAX_STANDARD_SHANTEN);
        handActionsService.forAllTiles(((rank, suit) -> {
            if (hand.hasPair(rank, suit)) {
                hand.removePair(rank, suit);
                minShanten.setValue(Math.min(
                        minShanten.intValue(),
                        getShantenRemovingSets(hand, MahjongConstants.SHANTEN_FOR_PAIR, 0)
                ));
                hand.addPair(rank, suit);
            }
        }));
        minShanten.setValue(Math.min(minShanten.intValue(), getShantenRemovingSets(hand, 0, 0)));
        return minShanten.intValue();
    }

    private Integer getShantenRemovingSets(Hand hand, int countedShantens, int sets) {

        final MutableInt minShanten = new MutableInt(MahjongConstants.MAX_STANDARD_SHANTEN - countedShantens);

        for (final PatternType type : MahjongConstants.FULL_PATTERN_TYPES) {
            handActionsService.forAllTiles(((rank, suit) -> {
                if (hand.hasSet(rank, suit, type)) {
                    hand.removeSet(rank, suit, type);
                    minShanten.setValue(Math.min(
                            minShanten.intValue(),
                            getShantenRemovingSets(hand, countedShantens + MahjongConstants.SHANTEN_FOR_SET, sets + 1)
                    ));
                    hand.addSet(rank, suit, type);
                }
            }));
        }

        // if no full sets has been found - proceeding to partial sets
        if (minShanten.intValue() == MahjongConstants.MAX_STANDARD_SHANTEN - countedShantens) {
            minShanten.setValue(Math.min(minShanten.intValue(), getShantenRemovingPartialSets(hand, countedShantens, sets)));
        }

        return minShanten.intValue();
    }

    private Integer getShantenRemovingPartialSets(Hand hand, int countedShantens, int sets) {

        final MutableInt minShanten = new MutableInt(MahjongConstants.MAX_STANDARD_SHANTEN - countedShantens);

        if (sets < MahjongConstants.MAX_SETS) {
            for (final PatternType type : MahjongConstants.PARTIAL_PATTERN_TYPES) {
                handActionsService.forAllTiles(((rank, suit) -> {
                    if (hand.hasSet(rank, suit, type)) {
                        hand.removeSet(rank, suit, type);
                        minShanten.setValue(Math.min(
                                minShanten.intValue(),
                                getShantenRemovingPartialSets(hand, countedShantens + MahjongConstants.SHANTEN_FOR_PARTIAL_SET, sets + 1)
                        ));
                        hand.addSet(rank, suit, type);
                    }
                }));
            }
        }

        return minShanten.intValue();
    }

}
