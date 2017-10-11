package com.games.boardgames.aeonsend.cards;

import com.games.boardgames.aeonsend.enums.CardType;
import com.games.boardgames.aeonsend.enums.Expansion;
import com.games.boardgames.aeonsend.enums.PriceRange;

/**
 * Created by honza on 3.9.17.
 */

public class SupplyCard extends Card {

    protected PriceRange price;

    public SupplyCard(int id, String name, CardType type, String picture, PriceRange price, Expansion expansion) {
        super(id, name, type, picture, expansion);
        setPrice(price);
    }

    public SupplyCard(String name, CardType type, String picture, PriceRange price, Expansion expansion) {
        super(name, type, picture, expansion);
        setPrice(price);
    }

    public PriceRange getPrice() {
        return price;
    }

    public void setPrice(PriceRange price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SupplyCard card = (SupplyCard) o;

        if (getName() != null ? !getName().equals(card.getName()) : card.getName() != null)
            return false;
        if (getType() != card.getType()) return false;
        if (price != card.price) return false;
        if (getPicture() != null ? !getPicture().equals(card.getPicture()) : card.getPicture() != null)
            return false;
        return getExpansion() == card.getExpansion();

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (getPicture() != null ? getPicture().hashCode() : 0);
        result = 31 * result + (getExpansion() != null ? getExpansion().hashCode() : 0);
        return result;
    }
}
