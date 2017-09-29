package com.example.honza.aeonsend.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.honza.aeonsend.cards.Card;
import com.example.honza.aeonsend.cards.CharacterCard;
import com.example.honza.aeonsend.cards.ExpansionCard;
import com.example.honza.aeonsend.cards.NemesisCard;
import com.example.honza.aeonsend.cards.SupplyCard;
import com.example.honza.aeonsend.enums.CardType;
import com.example.honza.aeonsend.enums.Expansion;
import com.example.honza.aeonsend.enums.PriceRange;
import com.example.honza.aeonsend.enums.TableColumns;
import com.example.honza.aeonsend.utils.Constants;

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
    private static DatabaseHandler mDatabaseInstance = null;
    private Context mContext;

    public static DatabaseHandler getInstance(Context context) {
        if (mDatabaseInstance == null) {
            mDatabaseInstance = new DatabaseHandler(context.getApplicationContext());
        }
        return mDatabaseInstance;
    }

//    public DatabaseHandler(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }

    private DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        Log.d("AEDB", "onCreate: Creating DB");

        // Create empty DB

        for (String tableName : Constants.tables) {
            switch (tableName) {
                case "expansion":
                    sqLiteDatabase.execSQL(createExpansionTable());
                    for (ExpansionCard card : CardList.getExpansionCardList()) {
                        addCard(sqLiteDatabase, card, tableName);
                    }
                    break;
                case "nemesis":
                    // Create Nemesis Table and insert cards
                    sqLiteDatabase.execSQL(createNemesisTable());
                    for (NemesisCard card : CardList.getNemesisCardList()) {
                        addCard(sqLiteDatabase, card, tableName);
                    }
                    break;
                // Create Character Table and insert cards
                case "character":
                    sqLiteDatabase.execSQL(createCharacterTable());
                    for (CharacterCard card : CardList.getCharacterCardList()) {
                        addCard(sqLiteDatabase, card, tableName);
                    }
                    break;
                // Create Gem Table and insert cards
                case "gem":
                    sqLiteDatabase.execSQL(createMarketTable(tableName));
                    for (SupplyCard card : CardList.getGemCardList()) {
                        addCard(sqLiteDatabase, card, tableName);
                    }
                    break;
                // Create Relic Table and insert cards
                case "relic":
                    sqLiteDatabase.execSQL(createMarketTable(tableName));
                    for (SupplyCard card : CardList.getRelicCardList()) {
                        addCard(sqLiteDatabase, card, tableName);
                    }
                    break;
                // Create Spell Table and insert cards
                case "spell":
                    sqLiteDatabase.execSQL(createMarketTable(tableName));
                    for (SupplyCard card : CardList.getSpellCardList()) {
                        addCard(sqLiteDatabase, card, tableName);
                    }
                    break;
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        for (String table : Constants.tables) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + table);
        }
        onCreate(sqLiteDatabase);
    }

    @Override
    public boolean addCard(SQLiteDatabase db, Card card, String tableName, ContentValues values) {
//        SQLiteDatabase db = this.getWritableDatabase();

        values.put(TableColumns.KEY_NAME.getValue(), card.getName());
        values.put(TableColumns.KEY_TYPE.getValue(), card.getType().getValue());
        values.put(TableColumns.KEY_PICTURE.getValue(), card.getPicture());
        values.put(TableColumns.KEY_EXPANSION.getValue(), card.getExpansion().name());

        db.insert(tableName, null, values);
//        db.close();

        return true;
    }

    @Override
    public boolean addCard(SQLiteDatabase db, CharacterCard card, String tableName) {
        ContentValues values = new ContentValues();
        return addCard(db, card, tableName, values);
    }

    @Override
    public boolean addCard(SQLiteDatabase db, ExpansionCard card, String tableName) {
        ContentValues values = new ContentValues();
        return addCard(db, card, tableName, values);
    }


    @Override
    public boolean addCard(SQLiteDatabase db, NemesisCard card, String tableName) {
        ContentValues values = new ContentValues();
        values.put(TableColumns.KEY_SETUPDESCRIPTION.getValue(), card.getSetupDescription());
        boolean result = addCard(db, card, tableName, values);
        return result;
    }

    @Override
    public boolean addCard(SQLiteDatabase db, SupplyCard card, String tableName) {
        ContentValues values = new ContentValues();
        values.put(TableColumns.KEY_PRICE.getValue(), card.getPrice().toString());
        boolean result = addCard(db, card, tableName, values);
        return result;
    }

    @Override
    public Card getCard(SQLiteDatabase db, int id, CardType type) {
//        SQLiteDatabase db = this.getReadableDatabase();

        String tableName = type.getValue();
        Cursor cursor = db.rawQuery("SELECT * from " + tableName + " where " +
                TableColumns.KEY_ID.getValue() + "=" + id, null);

        Card card = null;

        if (cursor.moveToFirst()) {
            try {
                card = Card.getCardFromCursor(cursor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        cursor.close();
        return card;
    }

    @Override
    public Card getCard(SQLiteDatabase db, String cardName, CardType type) {
        String tableName = type.getValue();
        Cursor cursor = db.rawQuery("SELECT * from " + tableName + " where " +
                TableColumns.KEY_NAME.getValue() + "=" + cardName, null);

        Card card = null;

        if (cursor.moveToFirst()) {
            try {
                card = Card.getCardFromCursor(cursor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        cursor.close();
        return card;
    }

    @Override
    // Return all cards that are applicable for entered type
    public List<Card> getCardsByPrice(SQLiteDatabase db, CardType type, Expansion[] expansions) throws Exception {
        return null;
//        return getCardsByPrice(db, type, PriceRange.ANY, );
    }

    @Override
    public List<Card> getCardsByPrice(SQLiteDatabase db, CardType type, PriceRange price, Expansion[] expansions) throws Exception {
//        SQLiteDatabase db = this.getReadableDatabase();
        String tableName = type.getValue();

        Cursor cursor = db.rawQuery("SELECT * from " + tableName + " where " +
                TableColumns.KEY_PRICE.getValue() + " >= " + price.getMinPrice() + " AND " +
                TableColumns.KEY_PRICE.getValue() + " <= " + price.getMaxPrice() + " AND " +
                "(" + getSQLExpansionQuery(expansions) + ")", null);

        Card card = null;
        List<Card> cardList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                card = Card.getCardFromCursor(cursor);
                cardList.add(card);
            } while (cursor.moveToNext());
        }

        cursor.close();
//        db.close();

        return cardList;
    }

    @Override
    public int updateCard(Card card, String tableName, ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();

        values.put(TableColumns.KEY_NAME.getValue(), card.getName());
        values.put(TableColumns.KEY_TYPE.getValue(), card.getType().getValue());
//        values.put(TableColumns.KEY_PRICE.getValue(), card.getPrice().toString());
        values.put(TableColumns.KEY_PICTURE.getValue(), card.getId());


        try {
            return db.update(tableName, values, TableColumns.KEY_ID.getValue() + " = ?",
                    new String[]{String.valueOf(card.getId())});
        } finally {
            db.close();
        }
    }

    @Override
    public int updateCard(NemesisCard card) {
        ContentValues values = new ContentValues();
        values.put(TableColumns.KEY_SETUPDESCRIPTION.getValue(), card.getSetupDescription());
        return updateCard(card, card.getType().getValue(), values);
    }

    @Override
    public int updateCard(CharacterCard card) {
        ContentValues values = new ContentValues();
        return updateCard(card, card.getType().getValue(), values);
    }

    @Override
    public int updateCard(SupplyCard card) {
        ContentValues values = new ContentValues();
        values.put(TableColumns.KEY_PRICE.getValue(), card.getPrice().toString());
        return updateCard(card, card.getType().getValue(), values);
    }

    @Override
    public int deleteCard(int id, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(tableName, TableColumns.KEY_ID.getValue() + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return result;
    }

    @Override
    public boolean deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();

        for (String tableName : Constants.tables) {
            db.delete(tableName, null, null);
        }
        db.close();

        return true;
    }

    @Override
    public List<Card> getAll(SQLiteDatabase db, CardType type, Expansion[] expansions) {

        String tableName = type.getValue();
        String expansionQuery = getSQLExpansionQuery(expansions);
        Cursor cursor = db.rawQuery("SELECT * from " + tableName + " where " + expansionQuery, null);

        Card card = null;
        List<Card> cardList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                try {
                    card = Card.getCardFromCursor(cursor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cardList.add(card);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return cardList;
    }

    private String getSQLExpansionQuery(Expansion[] expansions) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < expansions.length; i++) {
            sb.append(TableColumns.KEY_EXPANSION.getValue() + "='" + expansions[i].name() + "'");
            if (i != (expansions.length - 1)) {
                sb.append(" OR ");
            } else {
                break;
            }
        }
        return sb.toString();
    }

    private StringBuilder createTableCommonColumns(String tableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + tableName + "(" +
                TableColumns.KEY_ID.getValue() + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TableColumns.KEY_NAME.getValue() + " TEXT," +
                TableColumns.KEY_TYPE.getValue() + " TEXT," +
                TableColumns.KEY_PICTURE.getValue() + " TEXT," +
                TableColumns.KEY_EXPANSION.getValue() + " TEXT");
        return sb;
    }

    private String createNemesisTable() {
        StringBuilder sb = createTableCommonColumns(Constants.NEMESISTABLE);
        sb.append("," + TableColumns.KEY_SETUPDESCRIPTION.getValue() + " TEXT" + ")");

        return sb.toString();
//        String CREATE_TABLE = "CREATE TABLE " + Constants.NEMESISTABLE + "(" +
//                TableColumns.KEY_ID.getValue() + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                TableColumns.KEY_NAME.getValue() + " TEXT," +
//                TableColumns.KEY_TYPE.getValue() + " TEXT," +
//                TableColumns.KEY_PICTURE.getValue() + " TEXT," +
//                TableColumns.KEY_EXPANSION.getValue() + " TEXT," +
//                TableColumns.KEY_SETUPDESCRIPTION.getValue() + " TEXT" + ")";
//        return CREATE_TABLE;
    }

    private String createCharacterTable() {
        StringBuilder sb = createTableCommonColumns(Constants.CHARACTERTABLE);
        sb.append(")");

        return sb.toString();

//        String CREATE_TABLE = "CREATE TABLE " + Constants.CHARACTERTABLE + "(" +
//                TableColumns.KEY_ID.getValue() + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                TableColumns.KEY_NAME.getValue() + " TEXT," +
//                TableColumns.KEY_TYPE.getValue() + " TEXT," +
//                TableColumns.KEY_PICTURE.getValue() + " TEXT," +
//                TableColumns.KEY_EXPANSION.getValue() + " TEXT" + ")";
//        return CREATE_TABLE;
    }

    private String createExpansionTable() {
        StringBuilder sb = createTableCommonColumns(Constants.EXPANSIONTABLE);
        sb.append(")");

        return sb.toString();
//        String CREATE_TABLE = "CREATE TABLE " + Constants.EXPANSIONTABLE + "(" +
//                TableColumns.KEY_ID.getValue() + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                TableColumns.KEY_NAME.getValue() + " TEXT," +
//                TableColumns.KEY_TYPE.getValue() + " TEXT," +
//                TableColumns.KEY_PICTURE.getValue() + " TEXT," +
//                TableColumns.KEY_EXPANSION.getValue() + " TEXT" + ")";
//        return CREATE_TABLE;
    }

    private String createMarketTable(String tableName) {
        StringBuilder sb = createTableCommonColumns(tableName);
        sb.append("," + TableColumns.KEY_PRICE.getValue() + " INTEGER" + ")");

        return sb.toString();
//        String CREATE_TABLE = "CREATE TABLE " + tableName + "(" +
//                TableColumns.KEY_ID.getValue() + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                TableColumns.KEY_NAME.getValue() + " TEXT," +
//                TableColumns.KEY_TYPE.getValue() + " TEXT," +
//                TableColumns.KEY_PICTURE.getValue() + " TEXT," +
//                TableColumns.KEY_PRICE.getValue() + " INTEGER, " +
//                TableColumns.KEY_EXPANSION.getValue() + " TEXT" + ")";
//        return CREATE_TABLE;
    }
}
