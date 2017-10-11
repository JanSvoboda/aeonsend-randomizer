package com.games.boardgames.aeonsend.cards;

import android.database.Cursor;

import com.games.boardgames.aeonsend.enums.CardType;
import com.games.boardgames.aeonsend.enums.Expansion;
import com.games.boardgames.aeonsend.enums.PriceRange;
import com.games.boardgames.aeonsend.enums.TableColumns;

import java.io.Serializable;

/**
 * Created by honza on 3.9.17.
 */

public class Card implements Serializable {
    protected int id;
    protected String name;
    protected CardType type;
    protected String picture;
    protected Expansion expansion;

    public Card(int id, String name, CardType type, String picture, Expansion expansion) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.picture = picture;
        this.expansion = expansion;
    }

    public Card(String name, CardType type, String picture, Expansion expansion) {
        this.name = name;
        this.type = type;
        this.picture = picture;
        this.expansion = expansion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Expansion getExpansion() {
        return expansion;
    }

    public void setExpansion(Expansion expansion) {
        this.expansion = expansion;
    }

    // Map obtained cursor data to Card object and return correct object based on the value of type
    public static Card getCardFromCursor(Cursor cursor) throws Exception {
        int id = cursor.getInt(cursor.getColumnIndex(TableColumns.KEY_ID.getValue()));
        String name = cursor.getString(cursor.getColumnIndex(TableColumns.KEY_NAME.getValue()));
        CardType type = CardType.fromString(cursor.getString(cursor.getColumnIndex(TableColumns.KEY_TYPE.getValue())));
        String picture = cursor.getString(cursor.getColumnIndex(TableColumns.KEY_PICTURE.getValue()));
        Expansion expansion = Expansion.valueOf(cursor.getString(cursor.getColumnIndex(TableColumns.KEY_EXPANSION.getValue())));
        PriceRange price = null;

        switch (type) {
            case EXPANSION:
                return new ExpansionCard(id, name, type, picture, expansion);

            case CHARACTER:
                return new CharacterCard(id, name, type, picture, expansion);

            case NEMESIS:
                String setupDescription = cursor.getString(cursor.getColumnIndex(TableColumns.KEY_SETUPDESCRIPTION.getValue()));
                return new NemesisCard(id, name, type, picture, expansion, setupDescription);

            case GEM:
                price = PriceRange.fromString(cursor.getString(cursor.getColumnIndex(TableColumns.KEY_PRICE.getValue())));
                return new GemCard(id, name, type, picture, price, expansion);

            case RELIC:
                price = PriceRange.fromString(cursor.getString(cursor.getColumnIndex(TableColumns.KEY_PRICE.getValue())));
                return new RelicCard(id, name, type, picture, price, expansion);

            case SPELL:
                price = PriceRange.fromString(cursor.getString(cursor.getColumnIndex(TableColumns.KEY_PRICE.getValue())));
                return new SpellCard(id, name, type, picture, price, expansion);

            default:
                throw new Exception("Unknown type of card. Type in DB is: " + type.getValue());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (name != null ? !name.equals(card.name) : card.name != null) return false;
        if (type != card.type) return false;
        if (picture != null ? !picture.equals(card.picture) : card.picture != null) return false;
        return expansion == card.expansion;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (expansion != null ? expansion.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
