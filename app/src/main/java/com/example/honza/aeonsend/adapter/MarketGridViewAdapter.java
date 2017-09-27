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
import com.example.honza.aeonsend.cards.MarketSetupCard;
import com.example.honza.aeonsend.database.MarketSetupCardList;

/**
 * Created by honza on 14.9.17.
 */

public class MarketGridViewAdapter extends BaseAdapter {

    private final Context mContext;
    private final MarketSetupCard[] marketSetupCards;

    public MarketGridViewAdapter(Context mContext, MarketSetupCard[] marketSetupCards) {
        this.mContext = mContext;
        this.marketSetupCards = marketSetupCards;
    }

    @Override
    public int getCount() {
        return MarketSetupCardList.getMarketSetupCards().length;
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
        final MarketSetupCard marketSetupCard = marketSetupCards[position];

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.grid_item_marketsetup_card, null);
        }

        final ImageView imageView = view.findViewById(R.id.grid_market_setup_image);
        final TextView textView = view.findViewById(R.id.grid_market_setup_text);

        imageView.setImageResource(marketSetupCard.getImage());
        textView.setText(marketSetupCard.getName());

        return view;
    }
}
