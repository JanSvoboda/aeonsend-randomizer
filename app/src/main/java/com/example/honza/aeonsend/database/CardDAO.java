package com.example.honza.aeonsend.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.honza.aeonsend.cards.Card;
import com.example.honza.aeonsend.cards.CharacterCard;
import com.example.honza.aeonsend.cards.GemCard;
import com.example.honza.aeonsend.cards.NemesisCard;
import com.example.honza.aeonsend.cards.RelicCard;
import com.example.honza.aeonsend.cards.SpellCard;
import com.example.honza.aeonsend.cards.SupplyCard;
import com.example.honza.aeonsend.enums.CardType;
import com.example.honza.aeonsend.enums.Expansion;
import com.example.honza.aeonsend.enums.PriceRange;

import java.util.List;

/**
 * Created by honza on 3.9.17.
 */

public interface CardDAO {
    boolean addCard(SQLiteDatabase db, Card card, String tableName, ContentValues values);

    boolean addCard(SQLiteDatabase db, CharacterCard card, String tableName);

    boolean addCard(SQLiteDatabase db, NemesisCard card, String tableName);

    boolean addCard(SQLiteDatabase db, SupplyCard card, String tableName);

    Card getCard(SQLiteDatabase db, int id, CardType type);

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
