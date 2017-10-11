package com.games.boardgames.aeonsend.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.games.boardgames.aeonsend.R;
import com.games.boardgames.aeonsend.cards.Card;
import com.games.boardgames.aeonsend.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by honza on 25.9.17.
 */

public class GeneratedMarketGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<Card> cards;

    // Important for ImageView refresh per View - alternative way of GridView refresh. Stores all ImageViews
    final private List<ImageView> imageViewList = new ArrayList<>();

    public GeneratedMarketGridViewAdapter(Context mContext, List<Card> cards) {
        this.mContext = mContext;
        this.cards = cards;
    }

    @Override
    public int getCount() {
        return cards.size();
    }


    // Return Item from List of ImageViews
    @Override
    public Object getItem(int i) {
        return imageViewList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        // Get view for one character card
        Card card = cards.get(position);

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.market_fragment_gridview_item, null);
        }

        final ImageView imageView = view.findViewById(R.id.fragment_gridview_image);
//        final TextView textView = view.findViewById(R.id.fragment_gridview_text);
        imageView.setImageResource(view.getResources().getIdentifier(card.getPicture(), Constants.DRAWABLEDEFTYPE, Constants.PACKAGENAME));
//        textView.setText(card.getName());

        // Store ImageView into List that is used for fetching an ImageView at position i
        imageViewList.add(imageView);


        return view;
    }
}
