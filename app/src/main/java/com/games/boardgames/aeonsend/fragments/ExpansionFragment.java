package com.games.boardgames.aeonsend.fragments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.games.boardgames.aeonsend.R;
import com.games.boardgames.aeonsend.cards.ExpansionCard;
import com.games.boardgames.aeonsend.database.DatabaseHandler;
import com.games.boardgames.aeonsend.enums.CardType;
import com.games.boardgames.aeonsend.enums.Expansion;
import com.games.boardgames.aeonsend.utils.Constants;
import com.games.boardgames.aeonsend.listeners.OnDataPass;
import com.games.boardgames.aeonsend.listeners.OnExpansionsChange;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by honza on 29.9.17.
 */

public class ExpansionFragment extends Fragment {

    private HashMap<String, ExpansionCard> selectedExpansionsMap;
    private ExpansionCard depthsExpansionCard;
    private ExpansionCard namelessExpansionCard;
    private List<Expansion> expansionList = new ArrayList<>();
    private OnDataPass dataPasser;
    private OnExpansionsChange expansionChanger;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
        expansionChanger = (OnExpansionsChange) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Add always present Basic part of the game
        expansionList.add(Expansion.BASIC);

        if (savedInstanceState == null) {
            selectedExpansionsMap = new HashMap<>();
            depthsExpansionCard = getExpansionCard("The Depths");
            namelessExpansionCard = getExpansionCard("Nameless");
        } else {
            selectedExpansionsMap = (HashMap<String, ExpansionCard>) savedInstanceState.getSerializable("selectedExpansionsMap");
            depthsExpansionCard = selectedExpansionsMap.get("The Depths");
            if (depthsExpansionCard == null) {
                depthsExpansionCard = getExpansionCard("The Depths");
            } else {
                expansionList.add(Expansion.DEPTHS);
            }
            namelessExpansionCard = selectedExpansionsMap.get("Nameless");
            if (namelessExpansionCard == null) {
                namelessExpansionCard = getExpansionCard("Nameless");
            } else {
                expansionList.add(Expansion.NAMELESS);
            }
        }

        passData(expansionList);

        View view = inflater.inflate(R.layout.expansion_fragment, container, false);

        final TextView selectedSummaryTextView = view.findViewById(R.id.expansion_fragment_selected_expansions_summary);
        selectedSummaryTextView.setText(getTextSummary());
        final ImageView depthsImageView = view.findViewById(R.id.expansion_fragment_depths_imageview);
        final ImageView namelessImageView = view.findViewById(R.id.expansion_fragment_nameless_imageview);

        depthsImageView.setImageResource(getResources().getIdentifier(depthsExpansionCard.getPicture(), Constants.DRAWABLEDEFTYPE, Constants.PACKAGENAME));
//        depthsImageView.setOnTouchListener(new ButtonHighlighterOnTouchListener(depthsImageView) {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    selectedExpansionsMap.add(depthsExpansionCard);
//                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    selectedExpansionsMap.remove(depthsExpansionCard);
//                }
//                return super.onTouch(view, motionEvent);
//            }
//        });

        final ImageView depthsCheckboxImageView = view.findViewById(R.id.expansion_fragment_depths_selected_imageview);
        depthsCheckboxImageView.setImageResource(depthsExpansionCard.isSelected() ? R.drawable.ic_check_box_white_24dp : R.drawable.ic_check_box_outline_blank_white_24dp);

        depthsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectExpansion(depthsExpansionCard, depthsCheckboxImageView);
                selectedSummaryTextView.setText(getTextSummary());
            }
        });


        namelessImageView.setImageResource(getResources().getIdentifier(namelessExpansionCard.getPicture(), Constants.DRAWABLEDEFTYPE, Constants.PACKAGENAME));
//        namelessImageView.setOnTouchListener(new ButtonHighlighterOnTouchListener(namelessImageView) {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    selectedExpansionsMap.add(namelessExpansionCard);
//                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    selectedExpansionsMap.remove(namelessExpansionCard);
//                }
//                return super.onTouch(view, motionEvent);
//            }
//        });

        final ImageView namelessCheckboxImageView = view.findViewById(R.id.expansion_fragment_nameless_selected_imageview);
        namelessCheckboxImageView.setImageResource(namelessExpansionCard.isSelected() ? R.drawable.ic_check_box_white_24dp : R.drawable.ic_check_box_outline_blank_white_24dp);

        namelessImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectExpansion(namelessExpansionCard, namelessCheckboxImageView);
                selectedSummaryTextView.setText(getTextSummary());
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
        outState.putSerializable("selectedExpansionsMap", (Serializable) selectedExpansionsMap);
    }

    private void selectExpansion(ExpansionCard expansionCard, ImageView checkBox) {
        expansionCard.toggleSelected();
        checkBox.setImageResource(expansionCard.isSelected() ? R.drawable.ic_check_box_white_24dp : R.drawable.ic_check_box_outline_blank_white_24dp);
        if (!expansionCard.isSelected()) {
            selectedExpansionsMap.remove(expansionCard.getName());
            expansionList.remove(expansionCard.getExpansion());
        } else {
            selectedExpansionsMap.put(expansionCard.getName(), expansionCard);
            expansionList.add(expansionCard.getExpansion());
        }
        passData(expansionList);
        expansionChanger.onExpansionsChange(expansionList.size() - 1);
    }

    private void passData(List list) {
        dataPasser.onDataPass(Constants.EXTRASSELECTEDEXPANSION, list);
    }

    private String getTextSummary() {
        switch (expansionList.size()) {
            case 1:
                return "NONE (Pull me)";
            case 2:
                return String.valueOf(expansionList.get(1).name());
            case 3:
                return expansionList.get(1) + ", " + expansionList.get(2);
        }
        return null;
    }
}
