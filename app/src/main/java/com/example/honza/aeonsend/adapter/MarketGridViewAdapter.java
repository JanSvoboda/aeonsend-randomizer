package com.example.honza.aeonsend.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.honza.aeonsend.R;
import com.example.honza.aeonsend.cards.MarketCard;
import com.example.honza.aeonsend.database.MarketCardList;

/**
 * Created by honza on 14.9.17.
 */

public class MarketGridViewAdapter extends BaseAdapter {

    private final Context mContext;
    private final MarketCard[] marketCards;

    public MarketGridViewAdapter(Context mContext, MarketCard[] marketCards) {
        this.mContext = mContext;
        this.marketCards = marketCards;
    }

    @Override
    public int getCount() {
        return MarketCardList.getMarketCards().length;
    }

    @Override
    public Object getItem(int i) {
        Log.d("GETITEM", "getItem: " + i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        Log.d("GETITEMID", "getItemId: " + i);
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final MarketCard marketCard = marketCards[position];

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.grid_item_market_card, null);
        }

        final ImageView imageView = view.findViewById(R.id.grid_market_setup_image);
        final TextView textView = view.findViewById(R.id.grid_market_setup_text);

        imageView.setImageResource(marketCard.getImage());
        textView.setText(marketCard.getName());

        return view;
    }
}
