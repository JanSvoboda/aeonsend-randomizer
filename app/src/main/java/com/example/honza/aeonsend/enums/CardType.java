package com.example.honza.aeonsend.enums;

/**
 * Created by honza on 3.9.17.
 */

public enum CardType {
    SPELL("spell"),
    RELIC("relic"),
    GEM("gem"),
    NEMESIS("nemesis"),
    CHARACTER("character");


    private String value;

    CardType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    private static final CardType[] copyOfValues = values();

    public static CardType fromString(String value) {
        for (CardType type : copyOfValues) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }
}
