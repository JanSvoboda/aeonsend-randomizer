package com.example.honza.aeonsend.utils;

import com.example.honza.aeonsend.cards.MarketSetupCard;
import com.example.honza.aeonsend.enums.Expansion;

import java.util.List;

/**
 * Created by honza on 2.10.17.
 */

public interface GetIntentExtras {
    public int getPlayers();
    public Expansion[] getExpansions();
    public MarketSetupCard getSetup();
}
