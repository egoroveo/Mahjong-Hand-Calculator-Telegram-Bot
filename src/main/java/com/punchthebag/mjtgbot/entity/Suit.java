package com.punchthebag.mjtgbot.entity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Suit {
    MAN(0, 'm'),
    PIN(1, 'p'),
    SOU(2, 's'),
    HONOR(3, 'z'),
    UNKNOWN(-1, 'u');

    private final int number;

    private final char letter;

    Suit(int number, char letter) {
        this.number = number;
        this.letter = letter;
    }

    public static final List<Character> SUITS = Arrays.stream(Suit.values())
            .map(suit -> suit.letter)
            .collect(Collectors.toList());

    public static int getNumberByLetter(char letter) {
        for(Suit e : values()) {
            if(e.letter == letter) return e.number;
        }
        return UNKNOWN.number;
    }

    public static char getLetterByNumber(int number) {
        for(Suit e : values()) {
            if(e.number == number) return e.letter;
        }
        return UNKNOWN.letter;
    }

    public static Suit getSuit(int number) {
        for(Suit e : values()) {
            if(e.number == number) return e;
        }
        return UNKNOWN;
    }

    public int getNumber() {
        return number;
    }

    public char getLetter() {
        return letter;
    }

}
