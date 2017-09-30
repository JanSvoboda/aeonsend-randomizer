package com.example.honza.aeonsend.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.honza.aeonsend.R;
import com.example.honza.aeonsend.cards.ExpansionCard;
import com.example.honza.aeonsend.database.DatabaseHandler;
import com.example.honza.aeonsend.enums.CardType;
import com.example.honza.aeonsend.utils.Constants;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by honza on 29.9.17.
 */

public class ExpansionFragment extends Fragment {

    private HashMap<String, ExpansionCard> selectedExpansionsMap;
    private ExpansionCard depthsExpansionCard;
    private ExpansionCard namelessExpansionCard;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            selectedExpansionsMap = new HashMap<>();
            depthsExpansionCard = getExpansionCard("The Depths");
            namelessExpansionCard = getExpansionCard("Nameless");

        } else {
            selectedExpansionsMap = (HashMap<String, ExpansionCard>) savedInstanceState.getSerializable("selectedExpansionsMap");
            depthsExpansionCard = selectedExpansionsMap.get("The Depths");
            if (depthsExpansionCard == null) {
                depthsExpansionCard = getExpansionCard("The Depths");
            }
            namelessExpansionCard = selectedExpansionsMap.get("Nameless");
            if (namelessExpansionCard == null) {
                namelessExpansionCard = getExpansionCard("Nameless");
            }
        }

        View view = inflater.inflate(R.layout.expansion_fragment, container, false);

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
        } else {
            selectedExpansionsMap.put(expansionCard.getName(), expansionCard);
        }
    }
}
