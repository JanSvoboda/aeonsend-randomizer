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
import com.example.honza.aeonsend.cards.CharacterCard;
import com.example.honza.aeonsend.database.DatabaseHandler;
import com.example.honza.aeonsend.enums.CardType;
import com.example.honza.aeonsend.enums.Expansion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by honza on 21.9.17.
 */

public class CharactersFragment extends Fragment {

    private GridView gridView;
    private GeneratedMarketGridViewAdapter generatedMarketGridViewAdapter;
    private List<Card> characterCards;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            getCharacterCards();
        } else {
            characterCards = (List) savedInstanceState.getSerializable("characterCards");
        }

        final View view = inflater.inflate(R.layout.characters_fragment, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.character_swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getCharacterCards();
                        generatedMarketGridViewAdapter = new GeneratedMarketGridViewAdapter(view.getContext(), characterCards);
                        gridView.invalidateViews();
                        gridView.setAdapter(generatedMarketGridViewAdapter);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1500);
            }
        });

        gridView = view.findViewById(R.id.characters_fragment_gridview);
        generatedMarketGridViewAdapter = new GeneratedMarketGridViewAdapter(view.getContext(), characterCards);
        gridView.setAdapter(generatedMarketGridViewAdapter);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("characterCards", (Serializable) characterCards);
    }

    private void getCharacterCards() {

        characterCards = new ArrayList<>();

        // TODO fake value - expansion will be taken from user's choice, but Expansion.BASIC is always included
        Expansion[] expansions = {Expansion.BASIC};

        // Get access to SQLite DB
        DatabaseHandler dh = DatabaseHandler.getInstance(getContext());
        SQLiteDatabase db = dh.getReadableDatabase();

        // Get all nemesis cards from DB
        List<Card> cards = dh.getAll(db, CardType.CHARACTER, expansions);

        for (int i = 0; i < 4; i++) {
            Random random = new Random();
            int value = random.nextInt(cards.size());

            CharacterCard characterCard = (CharacterCard) cards.get(value);
            cards.remove(value);

            characterCards.add(characterCard);
        }
        dh.close();

    }
}
