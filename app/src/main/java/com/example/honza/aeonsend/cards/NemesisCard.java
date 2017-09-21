package com.example.honza.aeonsend.cards;

import com.example.honza.aeonsend.enums.CardType;

/**
 * Created by honza on 3.9.17.
 */

public class NemesisCard extends Card {

    public NemesisCard(int id, String name, CardType type, String picture) {
        super(id, name, type, picture);
        setType(CardType.NEMESIS);
        setPrice(0);
    }

    public NemesisCard(String name, CardType type, String picture) {
        super(name, type, picture);
        setType(CardType.NEMESIS);
        setPrice(0);
    }
}
