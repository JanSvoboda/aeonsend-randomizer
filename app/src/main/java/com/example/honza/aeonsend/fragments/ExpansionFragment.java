package com.example.honza.aeonsend.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.honza.aeonsend.R;
import com.example.honza.aeonsend.cards.ExpansionCard;
import com.example.honza.aeonsend.database.DatabaseHandler;
import com.example.honza.aeonsend.enums.CardType;
import com.example.honza.aeonsend.listeners.ButtonHighlighterOnTouchListener;
import com.example.honza.aeonsend.utils.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by honza on 29.9.17.
 */

public class ExpansionFragment extends Fragment {

    private List<ExpansionCard> selectedExpansions;
    private ExpansionCard depthsExpansionCard;
    private ExpansionCard namelessExpansionCard;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            selectedExpansions = new ArrayList<>();

        } else {
            selectedExpansions = (List) savedInstanceState.getSerializable("selectedExpansions");
        }

        depthsExpansionCard = getExpansionCard("The Depths");
        namelessExpansionCard = getExpansionCard("Nameless");

        View view = inflater.inflate(R.layout.expansion_fragment, container, false);

        final ImageView depthsImageView = view.findViewById(R.id.expansion_fragment_depths_imageview);
        ImageView namelessImageView = view.findViewById(R.id.expansion_fragment_nameless_imageview);

        depthsImageView.setImageResource(getResources().getIdentifier(depthsExpansionCard.getPicture(), Constants.DRAWABLEDEFTYPE, Constants.PACKAGENAME));
        depthsImageView.setOnTouchListener(new ButtonHighlighterOnTouchListener(depthsImageView) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    selectedExpansions.add(depthsExpansionCard);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    selectedExpansions.remove(depthsExpansionCard);
                }
                return super.onTouch(view, motionEvent);
            }
        });

        namelessImageView.setOnTouchListener(new ButtonHighlighterOnTouchListener(namelessImageView));
        namelessImageView.setImageResource(getResources().getIdentifier(namelessExpansionCard.getPicture(), Constants.DRAWABLEDEFTYPE, Constants.PACKAGENAME));
        namelessImageView.setOnTouchListener(new ButtonHighlighterOnTouchListener(namelessImageView) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    selectedExpansions.add(namelessExpansionCard);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    selectedExpansions.remove(namelessExpansionCard);
                }
                return super.onTouch(view, motionEvent);
            }
        });

        return view;
    }

    private ExpansionCard getExpansionCard(String cardName) {
        DatabaseHandler mHandler = DatabaseHandler.getInstance(getContext());
        SQLiteDatabase mDatabase = mHandler.getReadableDatabase();

        ExpansionCard card = (ExpansionCard) mHandler.getCard(mDatabase, cardName, CardType.EXPANSION);

        mDatabase.close();
        mHandler.close();

        return card;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("selectedExpansions",(Serializable) selectedExpansions);
    }
}
