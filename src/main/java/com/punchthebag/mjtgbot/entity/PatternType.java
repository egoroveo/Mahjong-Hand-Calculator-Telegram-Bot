package com.punchthebag.mjtgbot.entity;

public enum PatternType {
    ONE(1),
    PAIR(2),
    SET(3),
    CONSEQUENTIAL_SET(1, 1, 1),
    OPEN_ENDED(1, 1),
    CLOSED(1, 0, 1);

    private final Integer[] pattern;

    PatternType(Integer... pattern) {
        this.pattern = pattern;
    }

    public Integer[] getPattern() {
        return pattern;
    }
}
