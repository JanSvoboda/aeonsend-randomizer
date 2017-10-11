package com.games.boardgames.aeonsend.utils;

import com.games.boardgames.aeonsend.cards.MarketSetupCard;
import com.games.boardgames.aeonsend.enums.Expansion;

/**
 * Created by honza on 2.10.17.
 */

public interface GetIntentExtras {
    public int getPlayers();

    public Expansion[] getExpansions();

    public MarketSetupCard getSetup();
}
