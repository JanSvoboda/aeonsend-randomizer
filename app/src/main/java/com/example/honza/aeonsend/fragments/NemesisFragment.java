package com.example.honza.aeonsend.fragments;

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
import com.example.honza.aeonsend.database.CardList;
import com.example.honza.aeonsend.utils.Constants;

/**
 * Created by honza on 21.9.17.
 */

public class NemesisFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.nemesis_fragment, container, false);
        ImageView imageView = view.findViewById(R.id.nemesis_fragment_image);
        TextView textView = view.findViewById(R.id.nemesis_fragment_text_setup_description);

        imageView.setImageResource(getResources().getIdentifier(CardList.getBasicNemesisCardList()[3].getPicture(), Constants.DRAWABLEDEFTYPE, Constants.PACKAGENAME));
        textView.setText(CardList.getBasicNemesisCardList()[3].getSetupDescription());
        return view;
    }
}
