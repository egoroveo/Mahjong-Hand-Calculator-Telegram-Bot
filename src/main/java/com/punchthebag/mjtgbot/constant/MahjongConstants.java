package com.punchthebag.mjtgbot.constant;

import com.punchthebag.mjtgbot.entity.PatternType;

import java.util.*;

public class MahjongConstants {

    public static final Integer PAIRS_COUNT_TO_WIN = 7;
    public static final Integer MAX_STANDARD_SHANTEN = 8;
    public static final Integer SUIT_COUNT = 3;
    public static final Integer RANKS_COUNT = 9;
    public static final Integer HONOR_SUIT = 3;
    public static final Integer HONOR_RANKS_COUNT = 7;
    public static final Integer FIRST_TERMINAL = 0;
    public static final Integer LAST_TERMINAL = 8;
    public static final Integer ORPHANS_COUNT = 13;
    public static final Integer SHANTEN_FOR_SET = 2;
    public static final Integer SHANTEN_FOR_PAIR = 1;
    public static final Integer SHANTEN_FOR_PARTIAL_SET = 1;
    public static final Integer HAND_TILE_COUNT = 14;

    public static final List<PatternType> PARTIAL_PATTERN_TYPES = Collections.unmodifiableList(Arrays.asList(
            PatternType.PAIR,
            PatternType.OPEN_ENDED,
            PatternType.CLOSED
    ));

    public static final List<PatternType> FULL_PATTERN_TYPES = Collections.unmodifiableList(Arrays.asList(
            PatternType.SET,
            PatternType.CONSEQUENTIAL_SET
    ));

    public static final List<Character> SUITS = Collections.unmodifiableList(Arrays.asList('m', 'p', 's', 'z'));

    public static final Map<Character, Integer> SUIT_NUMBERS = Collections.unmodifiableMap(Map.of(
            'm', 0,
            'p', 1,
            's', 2,
            'z', 3
            ));
}
