package com.games.boardgames.aeonsend.listeners;

import android.os.Bundle;

import com.games.boardgames.aeonsend.cards.MarketSetupCard;

import java.util.List;

/**
 * Created by honza on 2.10.17.
 */

public interface OnDataPass {
    public void onDataPass(String name, List list);

    public void onDataPass(String name, Integer i);

    public void onDataPass(String name, MarketSetupCard card);

    public Bundle getFragmentValuesBundle();
}
