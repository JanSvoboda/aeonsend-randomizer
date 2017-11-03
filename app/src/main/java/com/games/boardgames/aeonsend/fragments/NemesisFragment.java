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
import android.widget.ImageView;
import android.widget.TextView;

import com.games.boardgames.aeonsend.R;
import com.games.boardgames.aeonsend.cards.Card;
import com.games.boardgames.aeonsend.cards.NemesisCard;
import com.games.boardgames.aeonsend.database.DatabaseHandler;
import com.games.boardgames.aeonsend.enums.CardType;
import com.games.boardgames.aeonsend.enums.Expansion;
import com.games.boardgames.aeonsend.utils.Constants;
import com.games.boardgames.aeonsend.utils.GetIntentExtras;

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
    private GetIntentExtras getIntentExtras;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getIntentExtras = (GetIntentExtras) context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        // Check if there are some stored values. If yes, then get stored values and use them
        if (savedInstanceState == null) {
            getNemesisCard();
        } else {
            nemesisCard = (NemesisCard) savedInstanceState.getSerializable("nemesisCard");
        }
        // Create View and display chosen Nemesis
        View view = inflater.inflate(R.layout.nemesis_fragment, container, false);

        // Swipe to refresh functionality
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.nemesis_swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                // Wait 1500 ms before going further
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Get new nemesisCard
                        getNemesisCard();
                        // Set imageView to new picture
                        imageView.setImageResource(getResources().getIdentifier(nemesisCard.getPicture(), Constants.DRAWABLEDEFTYPE, Constants.PACKAGENAME));
                        // Set textView to new setup description
                        textView.setText(nemesisCard.getSetupDescription());
                        // Stop showing refreshing icon
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, Constants.REFRESHWAIT);
            }
        });

        imageView = view.findViewById(R.id.nemesis_fragment_image);
        textView = view.findViewById(R.id.nemesis_fragment_text_setup_description);

        imageView.setImageResource(getResources().getIdentifier(nemesisCard.getPicture(), Constants.DRAWABLEDEFTYPE, Constants.PACKAGENAME));
        textView.setText(nemesisCard.getSetupDescription());

        return view;
    }


    // Store nemesisCard for device orientation change ro switching between tabs
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("nemesisCard", nemesisCard);
    }

    private void getNemesisCard() {

        // Fetch array of Expansions from Intent Extras
        Expansion[] expansions = getIntentExtras.getExpansions();

        // Get access to SQLite DB
        DatabaseHandler mHandler = DatabaseHandler.getInstance(getContext());
        SQLiteDatabase mDatabase = mHandler.getReadableDatabase();

        // Get all nemesis cards from DB
        List<Card> cards = mHandler.getAll(mDatabase, CardType.NEMESIS, expansions);

        // Get random nemesis for play
        Random random = new Random();
        int value = random.nextInt(cards.size());

        nemesisCard = (NemesisCard) cards.get(value);

        mDatabase.close();

        mHandler.close();

    }
}
