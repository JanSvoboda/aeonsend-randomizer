package com.example.honza.aeonsend.database;

import com.example.honza.aeonsend.R;
import com.example.honza.aeonsend.cards.MarketSetupCard;
import com.example.honza.aeonsend.enums.PriceRange;

/**
 * Created by honza on 14.9.17.
 */

public class MarketSetupCardList {

    private static MarketSetupCard[] marketSetupCards = {
            new MarketSetupCard("Random Setup", R.drawable.market_setup_random,
                    new PriceRange[]{PriceRange.ANY, PriceRange.ANY, PriceRange.ANY},
                    new PriceRange[]{PriceRange.ANY, PriceRange.ANY},
                    new PriceRange[]{PriceRange.ANY, PriceRange.ANY, PriceRange.ANY, PriceRange.ANY}),

            new MarketSetupCard("Market Setup 1", R.drawable.market_setup_1,
                    new PriceRange[]{PriceRange.LESSTHANFOUR, PriceRange.FOUR, PriceRange.ANY},
                    new PriceRange[]{PriceRange.ANY, PriceRange.ANY},
                    new PriceRange[]{PriceRange.LESSTHANFIVE, PriceRange.LESSTHANFIVE, PriceRange.MORETHANFIVE, PriceRange.MORETHANFIVE}),

            new MarketSetupCard("Market Setup 2", R.drawable.market_setup_2,
                    new PriceRange[]{PriceRange.MORETHANTHREE, PriceRange.MORETHANTHREE, PriceRange.MORETHANTHREE},
                    new PriceRange[]{PriceRange.MORETHANFOUR, PriceRange.ANY},
                    new PriceRange[]{PriceRange.LESSTHANSIX, PriceRange.LESSTHANSIX, PriceRange.LESSTHANSIX, PriceRange.MORETHANSIX}),

            new MarketSetupCard("Market Setup 3", R.drawable.market_setup_3,
                    new PriceRange[]{PriceRange.LESSTHANFOUR, PriceRange.FOURORFIVE, PriceRange.FOURORFIVE},
                    new PriceRange[]{PriceRange.ANY},
                    new PriceRange[]{PriceRange.THREE, PriceRange.FOUR, PriceRange.MORETHANFIVE, PriceRange.MORETHANFIVE, PriceRange.MORETHANFIVE}),

            new MarketSetupCard("Market Setup 4", R.drawable.market_setup_4,
                    new PriceRange[]{PriceRange.MORETHANFOUR, PriceRange.ANY, PriceRange.ANY},
                    new PriceRange[]{PriceRange.LESSTHANFOUR, PriceRange.MORETHANFOUR, PriceRange.ANY},
                    new PriceRange[]{PriceRange.LESSTHANFIVE, PriceRange.MORETHANFIVE, PriceRange.ANY}),

            new MarketSetupCard("Market Setup 5", R.drawable.market_setup_5,
                    new PriceRange[]{PriceRange.TWO, PriceRange.THREE, PriceRange.FOUR, PriceRange.FIVE},
                    new PriceRange[]{PriceRange.ANY},
                    new PriceRange[]{PriceRange.FOUR, PriceRange.FIVE, PriceRange.SIX, PriceRange.MORETHANSIX}),

            new MarketSetupCard("Market Setup 6", R.drawable.market_setup_6,
                    new PriceRange[]{PriceRange.THREE, PriceRange.FOUR},
                    new PriceRange[]{PriceRange.LESSTHANFOUR, PriceRange.MORETHANFOUR, PriceRange.ANY},
                    new PriceRange[]{PriceRange.THREEORFOUR, PriceRange.FIVEORSIX, PriceRange.FIVEORSIX, PriceRange.MORETHANSIX})
    };

    public static MarketSetupCard[] getMarketSetupCards() {
        return marketSetupCards;
    }
}
