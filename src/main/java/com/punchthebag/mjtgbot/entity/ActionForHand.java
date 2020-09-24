package com.punchthebag.mjtgbot.entity;

@FunctionalInterface
public interface ActionForHand {
    void apply(Integer rank, Integer suit);
}
