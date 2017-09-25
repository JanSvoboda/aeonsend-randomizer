package com.example.honza.aeonsend.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.honza.aeonsend.R;
import com.example.honza.aeonsend.cards.CharacterCard;
import com.example.honza.aeonsend.utils.Constants;

/**
 * Created by honza on 25.9.17.
 */

public class CharacterGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private CharacterCard[] characterCards;

    public CharacterGridViewAdapter(Context mContext, CharacterCard[] characterCards) {
        this.mContext = mContext;
        this.characterCards = characterCards;
    }

    @Override
    public int getCount() {
        return characterCards.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        // Get view for one character card
        CharacterCard characterCard = characterCards[position];

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.fragment_gridview_item, null);
        }
        // TODO bullshit value
        final ImageView imageView = view.findViewById(R.id.fragment_gridview_image);
        imageView.setImageResource(view.getResources().getIdentifier(characterCard.getPicture(), Constants.DRAWABLEDEFTYPE, Constants.PACKAGENAME));

        return view;
    }
}
