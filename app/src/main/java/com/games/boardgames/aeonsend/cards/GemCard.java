package com.games.boardgames.aeonsend.cards;

import com.games.boardgames.aeonsend.enums.CardType;
import com.games.boardgames.aeonsend.enums.Expansion;
import com.games.boardgames.aeonsend.enums.PriceRange;

/**
 * Created by honza on 3.9.17.
 */

public class GemCard extends SupplyCard {

    public GemCard(int id, String name, CardType type, String picture, PriceRange price, Expansion expansion) {
        super(id, name, type, picture, price, expansion);
        setType(CardType.GEM);
    }

    public GemCard(String name, CardType type, String picture, PriceRange price, Expansion expansion) {
        super(name, type, picture, price, expansion);
        setType(CardType.GEM);
    }
}
