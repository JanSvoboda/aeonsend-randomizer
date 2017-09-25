package com.example.honza.aeonsend.cards;

import com.example.honza.aeonsend.enums.CardType;
import com.example.honza.aeonsend.enums.Expansion;
import com.example.honza.aeonsend.enums.PriceRange;

/**
 * Created by honza on 3.9.17.
 */

public class RelicCard extends SupplyCard {

    public RelicCard(int id, String name, CardType type, String picture, PriceRange price, Expansion expansion) {
        super(id, name, type, picture, price, expansion);
        setType(CardType.RELIC);
    }

    public RelicCard(String name, CardType type, String picture, PriceRange price, Expansion expansion) {
        super(name, type, picture, price, expansion);
        setType(CardType.RELIC);
    }
}
