package com.example.honza.aeonsend.enums;

/**
 * Created by honza on 7.9.17.
 */

public enum Expansion {
    BASIC(0), DEPTHS(1), NAMELESS(2);

    private int value;

    Expansion(int i) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
