package com.example.honza.aeonsend.database;

import com.example.honza.aeonsend.cards.Card;
import com.example.honza.aeonsend.enums.CardType;

import java.util.List;

/**
 * Created by honza on 3.9.17.
 */

public interface CardDAO {
    boolean addCard(Card card);

    Card getCard(int id);

    int updateCard(Card card);

    int deleteCard(int id);

    int deleteAll();

    List<Card> getAll();

    List<Card> getCardsByType(CardType type) throws Exception;

    List<Card> getCardsByType(CardType type, int price) throws Exception;

    List<Card> getCardsByType(CardType type, int minprice, int maxprice) throws Exception;

}
