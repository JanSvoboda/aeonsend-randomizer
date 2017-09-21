package com.example.honza.aeonsend.cards;

import com.example.honza.aeonsend.enums.CardType;

/**
 * Created by honza on 3.9.17.
 */

public class GemCard extends SupplyCard {

    public GemCard(int id, String name, CardType type, String picture, int price) {
        super(id, name, type, picture, price);
        setType(CardType.GEM);
    }

    public GemCard(String name, CardType type, String picture, int price) {
        super(name, type, picture, price);
        setType(CardType.GEM);
    }
}
