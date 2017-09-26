package com.example.honza.aeonsend.fragments;

import android.database.sqlite.SQLiteDatabase;
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
import com.example.honza.aeonsend.cards.NemesisCard;
import com.example.honza.aeonsend.database.CardList;
import com.example.honza.aeonsend.database.DatabaseHandler;
import com.example.honza.aeonsend.enums.CardType;
import com.example.honza.aeonsend.enums.Expansion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by honza on 21.9.17.
 */

public class CharactersFragment extends Fragment {

    private GridView gridView;
    private GeneratedMarketGridViewAdapter generatedMarketGridViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // TODO fake value - expansion will be taken from user's choice, but Expansion.BASIC is always included
        Expansion[] expansions = {Expansion.BASIC};

        // Get access to SQLite DB
        DatabaseHandler dh = DatabaseHandler.getInstance(getContext());
        SQLiteDatabase db = dh.getReadableDatabase();

        // Get all nemesis cards from DB
        List<Card> cards = dh.getAll(db, CardType.CHARACTER, expansions);

        List<Card> characterCards = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Random random = new Random();
            int value = random.nextInt(cards.size());

            CharacterCard characterCard = (CharacterCard) cards.get(value);
            cards.remove(value);

            characterCards.add(characterCard);
        }

        View view = inflater.inflate(R.layout.characters_fragment, container, false);

        gridView = view.findViewById(R.id.characters_fragment_gridview);
        generatedMarketGridViewAdapter = new GeneratedMarketGridViewAdapter(view.getContext(), characterCards);
        gridView.setAdapter(generatedMarketGridViewAdapter);

        return view;
    }
}
