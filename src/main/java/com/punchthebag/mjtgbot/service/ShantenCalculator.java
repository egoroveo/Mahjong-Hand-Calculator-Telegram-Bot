package com.punchthebag.mjtgbot.service;

import com.punchthebag.mjtgbot.constant.MahjongConstants;
import com.punchthebag.mjtgbot.entity.Hand;
import com.punchthebag.mjtgbot.entity.PatternType;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableInt;
import org.springframework.stereotype.Service;


@Service
public class ShantenCalculator {

    private HandUtils handUtils;

    public ShantenCalculator(HandUtils handUtils) {
        this.handUtils = handUtils;
    }

    public Integer getShanten(Hand hand) {
        //TODO: Add verification
        return Math.min(Math.min(getStandardShanten(hand), getPairsShanten(hand)), getOrphansShanten(hand));
    }

    // tiles left for the win by getting 7 pairs
    private Integer getPairsShanten(Hand hand) {
        final MutableInt shanten = new MutableInt(MahjongConstants.PAIRS_COUNT_TO_WIN - 1);
        handUtils.forAllTiles(hand, (rank, suit) -> {
            if (handUtils.hasPair(hand, rank, suit)) {
                shanten.decrement();
            }
        });
        return shanten.getValue();
    }

    // tiles left for the win by getting 13 orphans + pair
    private Integer getOrphansShanten(Hand hand) {

        final MutableInt shanten = new MutableInt(MahjongConstants.ORPHANS_COUNT);

        handUtils.forAllTiles(hand, ((rank, suit) -> {
            if (handUtils.isOrphan(rank, suit) && handUtils.hasTile(hand, rank, suit)) {
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
        handUtils.forAllTiles(hand, ((rank, suit) -> {
            if (handUtils.isOrphan(rank, suit) && handUtils.hasPair(hand, rank, suit)) {
                hasPair.setTrue();
            }
        }));
        return hasPair.booleanValue();
    }

    // tiles left for the win by getting 4 sets + 1 pair
    private Integer getStandardShanten(Hand hand) {
        // taking a pair and recursively calculate shantens
        final MutableInt minShanten = new MutableInt(MahjongConstants.MAX_STANDARD_SHANTEN);
        handUtils.forAllTiles(hand, ((rank, suit) -> {
            if (handUtils.hasPair(hand, rank, suit)) {
                handUtils.removePair(hand, rank, suit);
                minShanten.setValue(Math.min(
                        minShanten.intValue(),
                        getShantenRemovingSets(hand, MahjongConstants.SHANTEN_FOR_PAIR)
                ));
                handUtils.addPair(hand, rank, suit);
            }
        }));
        minShanten.setValue(Math.min(minShanten.intValue(), getShantenRemovingSets(hand, 0)));
        return minShanten.intValue();
    }

    private Integer getShantenRemovingSets(Hand hand, int countedShantens) {

        final MutableInt minShanten = new MutableInt(MahjongConstants.MAX_STANDARD_SHANTEN - countedShantens);

        for (final PatternType type : MahjongConstants.FULL_PATTERN_TYPES) {
            handUtils.forAllTiles(hand, ((rank, suit) -> {
                if (handUtils.hasSet(hand, rank, suit, type)) {
                    handUtils.removeSet(hand, rank, suit, type);
                    minShanten.setValue(Math.min(
                            minShanten.intValue(),
                            getShantenRemovingSets(hand, countedShantens + MahjongConstants.SHANTEN_FOR_SET)
                    ));
                    handUtils.addSet(hand, rank, suit, type);
                }
            }));
        }

        // if no full sets has been found - proceeding to partial sets
        if (minShanten.intValue() == MahjongConstants.MAX_STANDARD_SHANTEN - countedShantens) {
            minShanten.setValue(Math.min(minShanten.intValue(), getShantenRemovingPartialSets(hand, countedShantens)));
        }

        return minShanten.intValue();
    }

    private Integer getShantenRemovingPartialSets(Hand hand, int countedShantens) {

        final MutableInt minShanten = new MutableInt(MahjongConstants.MAX_STANDARD_SHANTEN - countedShantens);

        for (final PatternType type : MahjongConstants.PARTIAL_PATTERN_TYPES) {
            handUtils.forAllTiles(hand, ((rank, suit) -> {
                if (handUtils.hasSet(hand, rank, suit, type)) {
                    handUtils.removeSet(hand, rank, suit, type);
                    minShanten.setValue(Math.min(
                            minShanten.intValue(),
                            getShantenRemovingPartialSets(hand, countedShantens + MahjongConstants.SHANTEN_FOR_PARTIAL_SET)
                    ));
                    handUtils.addSet(hand, rank, suit, type);
                }
            }));
        }

        return minShanten.intValue();
    }

}
