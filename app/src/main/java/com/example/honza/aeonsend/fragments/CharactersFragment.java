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
import com.example.honza.aeonsend.cards.CharacterCard;
import com.example.honza.aeonsend.database.CardList;

/**
 * Created by honza on 21.9.17.
 */

public class CharactersFragment extends Fragment {

    private GridView gridView;
    private GeneratedMarketGridViewAdapter generatedMarketGridViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        CharacterCard[] characterCards = new CharacterCard[4];

        for (int i = 0; i < 4; i++) {
            CharacterCard characterCard = CardList.getBasicCharacterCardList()[0];
            characterCards[i] = characterCard;
        }

        View view = inflater.inflate(R.layout.characters_fragment, container, false);

        gridView = view.findViewById(R.id.characters_fragment_gridview);
        generatedMarketGridViewAdapter = new GeneratedMarketGridViewAdapter(view.getContext(), characterCards);
        gridView.setAdapter(generatedMarketGridViewAdapter);

        return view;
    }
}
