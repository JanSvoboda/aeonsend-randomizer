package com.example.honza.aeonsend.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.honza.aeonsend.cards.Card;
import com.example.honza.aeonsend.enums.CardType;
import com.example.honza.aeonsend.cards.CharacterCard;
import com.example.honza.aeonsend.cards.GemCard;
import com.example.honza.aeonsend.cards.NemesisCard;
import com.example.honza.aeonsend.cards.RelicCard;
import com.example.honza.aeonsend.cards.SpellCard;
import com.example.honza.aeonsend.enums.TableColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by honza on 4.9.17.
 */

public class DatabaseHandler extends SQLiteOpenHelper implements CardDAO {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "aeonsend";

    // Contacts table name
    private static String tableName = "cards";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create empty DB
        sqLiteDatabase.execSQL(CreateSQLString());

        //Enter characters into DB
        for (String[] cardValues : CardList.getBasicCharacterCardList()) {
            Card card = new CharacterCard(cardValues[0], CardType.valueOf(cardValues[1]), cardValues[3]);
            addCard(card);
        }

        //Enter nemesis into DB
        for (String[] cardValues : CardList.getBasicNemesisCardList()) {
            Card card = new NemesisCard(cardValues[0], CardType.valueOf(cardValues[1]), cardValues[3]);
            addCard(card);
        }

        //Enter gems into DB
        for (String[] cardValues : CardList.getBasicGemCardList()) {
            Card card = new GemCard(cardValues[0], CardType.valueOf(cardValues[1]), cardValues[3], Integer.valueOf(cardValues[2]));
            addCard(card);
        }

        //Enter relic into DB
        for (String[] cardValues : CardList.getBasicRelicCardList()) {
            Card card = new RelicCard(cardValues[0], CardType.valueOf(cardValues[1]), cardValues[3], Integer.valueOf(cardValues[2]));
            addCard(card);
        }

        //Enter spells into DB
        for (String[] cardValues : CardList.getBasicSpellCardList()) {
            Card card = new SpellCard(cardValues[0], CardType.valueOf(cardValues[1]), cardValues[3], Integer.valueOf(cardValues[2]));
            addCard(card);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(sqLiteDatabase);
    }

    @Override
    public boolean addCard(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TableColumns.KEY_NAME.getValue(), card.getName());
        values.put(TableColumns.KEY_TYPE.getValue(), card.getType().getValue());
        values.put(TableColumns.KEY_PRICE.getValue(), card.getPrice());
        values.put(TableColumns.KEY_PICTURE.getValue(), card.getId());

        db.insert(tableName, null, values);
        db.close();

        return true;
    }

    @Override
    public Card getCard(int id) {
        return null;
    }

    @Override
    // Return all cards that are applicable for entered type
    public List<Card> getCardsByType(CardType type) throws Exception {
        return getCardsByType(type, 0, 7);
    }

    @Override
    public List<Card> getCardsByType(CardType type, int price) throws Exception {
        int minprice = --price;
        int maxprice = ++price;

        return getCardsByType(type, minprice, maxprice);
    }

    @Override
    public List<Card> getCardsByType(CardType type, int minPrice, int maxPrice) throws Exception {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * from " + tableName + " where " +
                TableColumns.KEY_TYPE.getValue() + "=" + type.getValue() + " AND " +
                TableColumns.KEY_PRICE.getValue() + ">=" + minPrice + " AND " +
                TableColumns.KEY_PRICE.getValue() + "<=" + maxPrice, null);

        Card card;
        List<Card> cardList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                card = Card.getCardFromCursor(cursor);
                cardList.add(card);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return cardList;
    }

    @Override
    public int updateCard(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TableColumns.KEY_NAME.getValue(), card.getName());
        values.put(TableColumns.KEY_TYPE.getValue(), card.getType().getValue());
        values.put(TableColumns.KEY_PRICE.getValue(), card.getPrice());
        values.put(TableColumns.KEY_PICTURE.getValue(), card.getId());

        try {
            return db.update(tableName, values, TableColumns.KEY_ID.getValue() + " = ?",
                new String[] { String.valueOf(card.getId()) });
        } finally {
            db.close();
        }
    }

    @Override
    public int deleteCard(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(tableName, TableColumns.KEY_ID.getValue() + " = ?", new String[] {String.valueOf(id)});
        db.close();
        return result;
    }

    @Override
    public int deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(tableName, null, null);
        db.close();

        return result;
    }

    @Override
    public List<Card> getAll() {
        return null;
    }

    private String CreateSQLString() {
        String CREATE_TABLE = "CREATE TABLE " + tableName + "("
                + TableColumns.KEY_ID.getValue() + " INTEGER PRIMARY KEY," +
                TableColumns.KEY_NAME.getValue() + " TEXT," + TableColumns.KEY_TYPE.getValue() +
                " TEXT," + TableColumns.KEY_PRICE.getValue() + " INTEGER," +
                TableColumns.KEY_PICTURE.getValue() + " TEXT" + ")";
        return CREATE_TABLE;
    }
}
