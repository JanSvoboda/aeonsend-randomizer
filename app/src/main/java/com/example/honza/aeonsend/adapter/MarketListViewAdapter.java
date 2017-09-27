package com.example.honza.aeonsend.adapter;

import android.content.Context;
import android.graphics.Typeface;
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

public class MarketListViewAdapter extends BaseAdapter {

    private final Context mContext;
    private final MarketSetupCard[] marketSetupCards;

    public MarketListViewAdapter(Context mContext, MarketSetupCard[] marketSetupCards) {
        this.mContext = mContext;
        this.marketSetupCards = marketSetupCards;
    }

    @Override
    public int getCount() {
        return MarketSetupCardList.getMarketSetupCards().length;
    }

    @Override
    public Object getItem(int i) {
        Log.v("GETITEM", "getItem: " + i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        Log.v("GETITEMID", "getItemId: " + i);
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MarketSetupCard marketSetupCard = marketSetupCards[position];

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.line_item_market_card, null);
        }

        final ImageView imageView = convertView.findViewById(R.id.imageMarketSetup);
        final TextView textMarketSetupName = convertView.findViewById(R.id.textMarketSetupName);
        final TextView textGemDistribution = convertView.findViewById(R.id.textGemCardDistribution);
        final TextView textRelicDistribution = convertView.findViewById(R.id.textRelicCardDistribution);
        final TextView textSpellDistribution = convertView.findViewById(R.id.textSpellCardDistribution);


        imageView.setImageResource(marketSetupCard.getImage());
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        textMarketSetupName.setText(marketSetupCard.getName());
        textMarketSetupName.setAllCaps(true);
        textMarketSetupName.setTypeface(null, Typeface.BOLD);
        textGemDistribution.setText("Gems: " + MarketSetupCard.toStringPriceRange(MarketSetupCard.mapPriceRangeFromArray(marketSetupCard.getGemsPriceList())));
        textRelicDistribution.setText("Relics: " + MarketSetupCard.toStringPriceRange(MarketSetupCard.mapPriceRangeFromArray(marketSetupCard.getRelicsPriceList())));
        textSpellDistribution.setText("Spells: " + MarketSetupCard.toStringPriceRange(MarketSetupCard.mapPriceRangeFromArray(marketSetupCard.getSpellsPriceList())));

        return convertView;
    }
}
