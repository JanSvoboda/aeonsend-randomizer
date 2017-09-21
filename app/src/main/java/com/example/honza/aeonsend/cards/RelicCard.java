package com.example.honza.aeonsend.cards;

import com.example.honza.aeonsend.enums.CardType;

/**
 * Created by honza on 3.9.17.
 */

public class RelicCard extends SupplyCard {

    public RelicCard(int id, String name, CardType type, String picture, int price) {
        super(id, name, type, picture, price);
        setType(CardType.RELIC);
    }

    public RelicCard(String name, CardType type, String picture, int price) {
        super(name, type, picture, price);
        setType(CardType.RELIC);
    }
}
