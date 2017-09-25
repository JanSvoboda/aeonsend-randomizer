package com.example.honza.aeonsend.cards;

import com.example.honza.aeonsend.enums.CardType;
import com.example.honza.aeonsend.enums.Expansion;
import com.example.honza.aeonsend.enums.PriceRange;

/**
 * Created by honza on 3.9.17.
 */

public class SupplyCard extends Card {

    public SupplyCard(int id, String name, CardType type, String picture, PriceRange price, Expansion expansion) {
        super(id, name, type, picture, expansion);
        setPrice(price);
    }

    public SupplyCard(String name, CardType type, String picture, PriceRange price, Expansion expansion) {
        super(name, type, picture, expansion);
        setPrice(price);
    }
}
