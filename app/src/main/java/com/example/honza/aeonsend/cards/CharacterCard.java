package com.example.honza.aeonsend.cards;

import com.example.honza.aeonsend.enums.CardType;

/**
 * Created by honza on 3.9.17.
 */

public class CharacterCard extends Card {

    public CharacterCard(int id, String name, CardType type, String picture) {
        super(id, name, type, picture);
        setType(CardType.CHARACTER);
        setPrice(0);
    }

    public CharacterCard(String name, CardType type, String picture) {
        super(name, type, picture);
        setType(CardType.CHARACTER);
        setPrice(0);
    }
}
