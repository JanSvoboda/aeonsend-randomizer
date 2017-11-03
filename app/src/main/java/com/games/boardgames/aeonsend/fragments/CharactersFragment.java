package com.games.boardgames.aeonsend.fragments;

import android.content.Context;
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

import com.games.boardgames.aeonsend.R;
import com.games.boardgames.aeonsend.adapter.GeneratedMarketGridViewAdapter;
import com.games.boardgames.aeonsend.cards.Card;
import com.games.boardgames.aeonsend.cards.CharacterCard;
import com.games.boardgames.aeonsend.database.DatabaseHandler;
import com.games.boardgames.aeonsend.enums.CardType;
import com.games.boardgames.aeonsend.enums.Expansion;
import com.games.boardgames.aeonsend.utils.Constants;
import com.games.boardgames.aeonsend.utils.GetIntentExtras;

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
    private GetIntentExtras getIntentExtras;
    private int numPlayers;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getIntentExtras = (GetIntentExtras) context;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {

        // Get number of players from Activity
        numPlayers = getIntentExtras.getPlayers();

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
                }, Constants.REFRESHWAIT);
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

        // Get array of Expansions form Intent Extras
        Expansion[] expansions = getIntentExtras.getExpansions();

        // Get access to SQLite DB
        DatabaseHandler mHandler = DatabaseHandler.getInstance(getContext());
        SQLiteDatabase mDatabase = mHandler.getReadableDatabase();

        // Get all nemesis cards from DB
        List<Card> cards = mHandler.getAll(mDatabase, CardType.CHARACTER, expansions);

        for (int i = 0; i < numPlayers; i++) {
            Random random = new Random();
            int value = random.nextInt(cards.size());

            CharacterCard characterCard = (CharacterCard) cards.get(value);
            cards.remove(value);

            characterCards.add(characterCard);
        }

        mDatabase.close();
        mHandler.close();

    }
}
