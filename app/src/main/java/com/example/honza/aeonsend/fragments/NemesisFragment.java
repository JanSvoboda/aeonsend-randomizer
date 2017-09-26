package com.example.honza.aeonsend.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.honza.aeonsend.R;
import com.example.honza.aeonsend.cards.Card;
import com.example.honza.aeonsend.cards.NemesisCard;
import com.example.honza.aeonsend.database.CardList;
import com.example.honza.aeonsend.database.DatabaseHandler;
import com.example.honza.aeonsend.enums.CardType;
import com.example.honza.aeonsend.utils.Constants;

import java.util.List;
import java.util.Random;

/**
 * Created by honza on 21.9.17.
 */

public class NemesisFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        // Get access to SQLite DB
        DatabaseHandler dh = DatabaseHandler.getInstance(getContext());
        SQLiteDatabase db = dh.getReadableDatabase();

        // Get all nemesis cards from DB
        List<Card> cards = dh.getAll(db, CardType.NEMESIS);

        // Get random nemesis for play
        Random random = new Random();
        int value = random.nextInt(cards.size());

        NemesisCard nemesisCard = (NemesisCard) cards.get(value);

        // Create View and display chosen Nemesis
        View view =  inflater.inflate(R.layout.nemesis_fragment, container, false);
        ImageView imageView = view.findViewById(R.id.nemesis_fragment_image);
        TextView textView = view.findViewById(R.id.nemesis_fragment_text_setup_description);

        imageView.setImageResource(getResources().getIdentifier(nemesisCard.getPicture(), Constants.DRAWABLEDEFTYPE, Constants.PACKAGENAME));
        textView.setText(nemesisCard.getSetupDescription());

        dh.close();

        return view;
    }
}
