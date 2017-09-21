package com.example.honza.aeonsend.cards;

import com.example.honza.aeonsend.enums.CardType;

/**
 * Created by honza on 3.9.17.
 */

public class SupplyCard extends Card {

    public SupplyCard(int id, String name, CardType type, String picture, int price) {
        super(id, name, type, picture);
        this.price = price;
    }

    public SupplyCard(String name, CardType type, String picture, int price) {
        super(name, type, picture);
        this.price = price;
    }
}
