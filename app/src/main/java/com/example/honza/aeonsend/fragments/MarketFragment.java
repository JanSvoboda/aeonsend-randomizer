package com.example.honza.aeonsend.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.honza.aeonsend.R;
import com.example.honza.aeonsend.adapter.GeneratedMarketGridViewAdapter;
import com.example.honza.aeonsend.cards.Card;
import com.example.honza.aeonsend.cards.MarketSetupCard;
import com.example.honza.aeonsend.database.DatabaseHandler;
import com.example.honza.aeonsend.database.MarketSetupCardList;
import com.example.honza.aeonsend.enums.CardType;
import com.example.honza.aeonsend.enums.Expansion;
import com.example.honza.aeonsend.enums.PriceRange;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by honza on 21.9.17.
 */

public class MarketFragment extends Fragment {

    private GridView gridView;
    private GeneratedMarketGridViewAdapter generatedMarketGridViewAdapter;
    private List<Card> chosenCards;
    private DatabaseHandler mHandler = null;
    private SQLiteDatabase mDatabase = null;
    private Expansion[] expansions = null;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            getChosenCards();
        } else {
            chosenCards = (List) savedInstanceState.getSerializable("chosenCards");
        }

        final View view = inflater.inflate(R.layout.market_fragment, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.market_swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getChosenCards();
                        generatedMarketGridViewAdapter = new GeneratedMarketGridViewAdapter(view.getContext(), chosenCards);
                        gridView.invalidateViews();
                        gridView.setAdapter(generatedMarketGridViewAdapter);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1500);
            }
        });

        gridView = view.findViewById(R.id.market_fragment_gridview);
        generatedMarketGridViewAdapter = new GeneratedMarketGridViewAdapter(view.getContext(), chosenCards);
        gridView.setAdapter(generatedMarketGridViewAdapter);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("chosenCards", (Serializable) chosenCards);
    }

    @Override
    public void onDetach() {
        super.onDetach();

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

    private void getChosenCards() {

        // Get DatabaseHandler and Database
        mHandler = DatabaseHandler.getInstance(getContext());
        mDatabase = mHandler.getReadableDatabase();
        chosenCards = new ArrayList<>();

        //TODO fake value of MarketSetupCard - marketcard will be taken from user's choice, Expansion[] is set to Basic
        MarketSetupCard marketSetupCard = MarketSetupCardList.getMarketSetupCards()[3];
        expansions = new Expansion[]{Expansion.BASIC, Expansion.DEPTHS, Expansion.NAMELESS};

        // Create supply of cards for market

        for (PriceRange priceRange : marketSetupCard.getGemsPriceList()) {
            addToChosenCardList(priceRange, CardType.GEM);
        }

        for (PriceRange priceRange : marketSetupCard.getRelicsPriceList()) {
            addToChosenCardList(priceRange, CardType.RELIC);
        }

        for (PriceRange priceRange : marketSetupCard.getSpellsPriceList()) {
            addToChosenCardList(priceRange, CardType.SPELL);
        }

        mDatabase.close();
    }
}
