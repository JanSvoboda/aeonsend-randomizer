package com.example.honza.aeonsend.cards;

import android.database.Cursor;

import com.example.honza.aeonsend.enums.CardType;
import com.example.honza.aeonsend.enums.TableColumns;

/**
 * Created by honza on 3.9.17.
 */

public class Card {
    private int id;
    private String name;
    private CardType type;
    protected int price;
    private String picture;

    public Card(int id, String name, CardType type, String picture) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.picture = picture;
    }

    public Card(String name, CardType type, String picture) {
        this.name = name;
        this.type = type;
        this.picture = picture;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public static Card getCardFromCursor(Cursor cursor) throws Exception {
        int id = cursor.getInt(cursor.getColumnIndex(TableColumns.KEY_ID.getValue()));
        String name = cursor.getString(cursor.getColumnIndex(TableColumns.KEY_NAME.getValue()));
        CardType type = CardType.valueOf(cursor.getString(cursor.getColumnIndex(TableColumns.KEY_TYPE.getValue())));
        int price = cursor.getInt(cursor.getColumnIndex(TableColumns.KEY_PRICE.getValue()));
        String picture = cursor.getString(cursor.getColumnIndex(TableColumns.KEY_PICTURE.getValue()));

        switch (type) {
            case CHARACTER:
                return new CharacterCard(id, name, type, picture);

            case NEMESIS:
                return new NemesisCard(id, name, type, picture);

            case GEM:
                return new GemCard(id, name, type, picture, price);

            case RELIC:
                return new RelicCard(id, name, type, picture, price);

            case SPELL:
                return new SpellCard(id, name, type, picture, price);

            default:
                throw new Exception("Unknown type of card. Type in DB is: " + type.getValue());
        }
    }
}
