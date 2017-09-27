package com.example.honza.aeonsend.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.honza.aeonsend.R;
import com.example.honza.aeonsend.adapter.GeneratedMarketGridViewAdapter;
import com.example.honza.aeonsend.cards.Card;
import com.example.honza.aeonsend.cards.NemesisCard;
import com.example.honza.aeonsend.database.CardList;
import com.example.honza.aeonsend.database.DatabaseHandler;
import com.example.honza.aeonsend.enums.CardType;
import com.example.honza.aeonsend.enums.Expansion;
import com.example.honza.aeonsend.enums.PriceRange;
import com.example.honza.aeonsend.utils.Constants;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * Created by honza on 21.9.17.
 */

public class NemesisFragment extends Fragment {

    private NemesisCard nemesisCard;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView imageView;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            getNemesisCard();
        } else {
            nemesisCard = (NemesisCard) savedInstanceState.getSerializable("nemesisCard");
        }
        // Create View and display chosen Nemesis
        View view = inflater.inflate(R.layout.nemesis_fragment, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.nemesis_swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getNemesisCard();
                        imageView.setImageResource(getResources().getIdentifier(nemesisCard.getPicture(), Constants.DRAWABLEDEFTYPE, Constants.PACKAGENAME));
                        textView.setText(nemesisCard.getSetupDescription());
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1500);
            }
        });

        imageView = view.findViewById(R.id.nemesis_fragment_image);
        textView = view.findViewById(R.id.nemesis_fragment_text_setup_description);

        imageView.setImageResource(getResources().getIdentifier(nemesisCard.getPicture(), Constants.DRAWABLEDEFTYPE, Constants.PACKAGENAME));
        textView.setText(nemesisCard.getSetupDescription());

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("nemesisCard", nemesisCard);
    }

    private void getNemesisCard() {

        // TODO fake value - expansion will be taken from user's choice, but Expansion.BASIC is always included
        Expansion[] expansions = {Expansion.BASIC, Expansion.DEPTHS, Expansion.NAMELESS};

        // Get access to SQLite DB
        DatabaseHandler dh = DatabaseHandler.getInstance(getContext());
        SQLiteDatabase db = dh.getReadableDatabase();

        // Get all nemesis cards from DB
        List<Card> cards = dh.getAll(db, CardType.NEMESIS, expansions);

        // Get random nemesis for play
        Random random = new Random();
        int value = random.nextInt(cards.size());

        nemesisCard = (NemesisCard) cards.get(value);

        dh.close();

    }
}
