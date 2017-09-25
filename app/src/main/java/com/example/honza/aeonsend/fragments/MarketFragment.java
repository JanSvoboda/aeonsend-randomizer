package com.example.honza.aeonsend.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.honza.aeonsend.R;
import com.example.honza.aeonsend.adapter.GeneratedMarketGridViewAdapter;
import com.example.honza.aeonsend.cards.Card;
import com.example.honza.aeonsend.cards.CharacterCard;
import com.example.honza.aeonsend.cards.GemCard;
import com.example.honza.aeonsend.cards.RelicCard;
import com.example.honza.aeonsend.cards.SpellCard;
import com.example.honza.aeonsend.database.CardList;

/**
 * Created by honza on 21.9.17.
 */

public class MarketFragment extends Fragment {

    private GridView gridView;
    private GeneratedMarketGridViewAdapter generatedMarketGridViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        Card[] cards = new Card[9];

        for (int i = 0; i < 3; i++) {
            GemCard gemCard = CardList.getBasicGemCardList()[0];
            cards[i] = gemCard;
        }

        for (int i = 3; i < 6; i++) {
            RelicCard relicCard = CardList.getBasicRelicCardList()[0];
            cards[i] = relicCard;
        }

        for (int i = 6; i < 9; i++) {
            SpellCard spellCard = CardList.getBasicSpellCardList()[0];
            cards[i] = spellCard;
        }

        View view = inflater.inflate(R.layout.market_fragment, container, false);

        gridView = view.findViewById(R.id.market_fragment_gridview);
        generatedMarketGridViewAdapter = new GeneratedMarketGridViewAdapter(view.getContext(), cards);
        gridView.setAdapter(generatedMarketGridViewAdapter);

        return view;
    }
}
