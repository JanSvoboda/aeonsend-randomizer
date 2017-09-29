package com.example.honza.aeonsend.cards;

import com.example.honza.aeonsend.enums.CardType;
import com.example.honza.aeonsend.enums.Expansion;

/**
 * Created by honza on 29.9.17.
 */

public class ExpansionCard extends Card {
    public ExpansionCard(int id, String name, CardType type, String picture, Expansion expansion) {
        super(id, name, type, picture, expansion);
    }

    public ExpansionCard(String name, CardType type, String picture, Expansion expansion) {
        super(name, type, picture, expansion);
    }
}
