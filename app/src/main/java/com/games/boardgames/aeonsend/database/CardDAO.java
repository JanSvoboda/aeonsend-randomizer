package com.games.boardgames.aeonsend.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.games.boardgames.aeonsend.cards.Card;
import com.games.boardgames.aeonsend.cards.CharacterCard;
import com.games.boardgames.aeonsend.cards.ExpansionCard;
import com.games.boardgames.aeonsend.cards.NemesisCard;
import com.games.boardgames.aeonsend.cards.SupplyCard;
import com.games.boardgames.aeonsend.enums.CardType;
import com.games.boardgames.aeonsend.enums.Expansion;
import com.games.boardgames.aeonsend.enums.PriceRange;

import java.util.List;

/**
 * Created by honza on 3.9.17.
 */

public interface CardDAO {
    boolean addCard(SQLiteDatabase db, Card card, String tableName, ContentValues values);

    boolean addCard(SQLiteDatabase db, CharacterCard card, String tableName);

    boolean addCard(SQLiteDatabase db, ExpansionCard card, String tableName);

    boolean addCard(SQLiteDatabase db, NemesisCard card, String tableName);

    boolean addCard(SQLiteDatabase db, SupplyCard card, String tableName);

    Card getCard(SQLiteDatabase db, int id, CardType type);

    Card getCard(SQLiteDatabase db, String cardName, CardType type);

    int updateCard(Card card, String tableName, ContentValues values);

    int updateCard(NemesisCard card);

    int updateCard(CharacterCard card);

    int updateCard(SupplyCard card);

    int deleteCard(int id, String tableName);

    boolean deleteAll();

    List<Card> getAll(SQLiteDatabase db, CardType type, Expansion[] expansions);

    List<Card> getCardsByPrice(SQLiteDatabase db, CardType type, Expansion[] expansions) throws Exception;

    List<Card> getCardsByPrice(SQLiteDatabase db, CardType type, PriceRange price, Expansion[] expansions) throws Exception;
}
