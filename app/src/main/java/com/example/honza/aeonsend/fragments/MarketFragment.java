package com.example.honza.aeonsend.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.honza.aeonsend.R;
import com.example.honza.aeonsend.adapter.GeneratedMarketGridViewAdapter;
import com.example.honza.aeonsend.cards.Card;
import com.example.honza.aeonsend.cards.CharacterCard;
import com.example.honza.aeonsend.cards.GemCard;
import com.example.honza.aeonsend.cards.MarketCard;
import com.example.honza.aeonsend.cards.RelicCard;
import com.example.honza.aeonsend.cards.SpellCard;
import com.example.honza.aeonsend.database.CardList;
import com.example.honza.aeonsend.database.DatabaseHandler;
import com.example.honza.aeonsend.database.MarketCardList;
import com.example.honza.aeonsend.enums.CardType;
import com.example.honza.aeonsend.enums.Expansion;
import com.example.honza.aeonsend.enums.PriceRange;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by honza on 21.9.17.
 */

public class MarketFragment extends Fragment {

    private GridView gridView;
    private GeneratedMarketGridViewAdapter generatedMarketGridViewAdapter;
    private List<Card> chosenCards = new ArrayList<>();
    private DatabaseHandler mHandler = null;
    private SQLiteDatabase mDatabase = null;
    private Expansion[] expansions = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Get DatabaseHandler and Database
        mHandler = DatabaseHandler.getInstance(getContext());
        mDatabase = mHandler.getReadableDatabase();

        //TODO fake value of MarketCard - marketcard will be taken from user's choice, Expansion[] is set to Basic
        MarketCard marketCard = MarketCardList.getMarketCards()[3];
        expansions = new Expansion[]{Expansion.BASIC, Expansion.DEPTHS, Expansion.NAMELESS};

        // Create supply of cards for market
        Card[] cards = new Card[9];

        for (PriceRange priceRange: marketCard.getGemsPriceList()) {
            addToChosenCardList(priceRange, CardType.GEM);
        }

        for (PriceRange priceRange: marketCard.getRelicsPriceList()) {
            addToChosenCardList(priceRange, CardType.RELIC);
        }

        for (PriceRange priceRange: marketCard.getSpellsPriceList()) {
            addToChosenCardList(priceRange, CardType.SPELL);
        }

        View view = inflater.inflate(R.layout.market_fragment, container, false);

        for (Card card: chosenCards) {
            Log.d("MFRAG", "onCreateView: " + card);
        }

        gridView = view.findViewById(R.id.market_fragment_gridview);
        generatedMarketGridViewAdapter = new GeneratedMarketGridViewAdapter(view.getContext(), chosenCards);
        gridView.setAdapter(generatedMarketGridViewAdapter);


        mDatabase.close();
        return view;
    }

    private void addToChosenCardList(PriceRange priceRange, CardType type) {
        try {
            // Get cards based on Price
            List<Card> applicableCards = mHandler.getCardsByPrice(mDatabase, type, priceRange, expansions);
            // Remove all cards that are duplicates of already chosen cards in chosenCards List
            applicableCards.removeAll(chosenCards);
            // Randomly select one card and add it to chosenCards
            Random random = new Random();
            int value = random.nextInt(applicableCards.size());
            chosenCards.add(applicableCards.get(value));
            applicableCards = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
