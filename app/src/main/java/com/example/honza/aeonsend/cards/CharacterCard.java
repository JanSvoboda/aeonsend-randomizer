package com.example.honza.aeonsend.cards;

import com.example.honza.aeonsend.enums.CardType;
import com.example.honza.aeonsend.enums.Expansion;
import com.example.honza.aeonsend.enums.PriceRange;

/**
 * Created by honza on 3.9.17.
 */

public class CharacterCard extends Card {

    public CharacterCard(int id, String name, CardType type, String picture, Expansion expansion) {
        super(id, name, type, picture, expansion);
    }

    public CharacterCard(String name, CardType type, String picture, Expansion expansion) {
        super(name, type, picture, expansion);
    }
}
