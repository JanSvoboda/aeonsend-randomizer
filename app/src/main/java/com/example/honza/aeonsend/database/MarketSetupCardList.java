package com.example.honza.aeonsend.database;

import com.example.honza.aeonsend.R;
import com.example.honza.aeonsend.cards.MarketSetupCard;
import com.example.honza.aeonsend.enums.PriceRange;

/**
 * Created by honza on 14.9.17.
 */

public class MarketSetupCardList {

    private static MarketSetupCard[] marketSetupCards = {
            new MarketSetupCard("random setup", R.drawable.market_setup_random,
                    new PriceRange[]{PriceRange.ANY, PriceRange.ANY, PriceRange.ANY},
                    new PriceRange[]{PriceRange.ANY, PriceRange.ANY},
                    new PriceRange[]{PriceRange.ANY, PriceRange.ANY, PriceRange.ANY, PriceRange.ANY}),

            new MarketSetupCard("setup one", R.drawable.market_setup_1,
                    new PriceRange[]{PriceRange.LESSTHANFOUR, PriceRange.FOUR, PriceRange.ANY},
                    new PriceRange[]{PriceRange.ANY, PriceRange.ANY},
                    new PriceRange[]{PriceRange.LESSTHANFIVE, PriceRange.LESSTHANFIVE, PriceRange.MORETHANFIVE, PriceRange.MORETHANFIVE}),

            new MarketSetupCard("setup two", R.drawable.market_setup_2,
                    new PriceRange[]{PriceRange.MORETHANTHREE, PriceRange.MORETHANTHREE, PriceRange.MORETHANTHREE},
                    new PriceRange[]{PriceRange.MORETHANFOUR, PriceRange.ANY},
                    new PriceRange[]{PriceRange.LESSTHANSIX, PriceRange.LESSTHANSIX, PriceRange.LESSTHANSIX, PriceRange.MORETHANSIX}),

            new MarketSetupCard("setup three", R.drawable.market_setup_3,
                    new PriceRange[]{PriceRange.LESSTHANFOUR, PriceRange.FOURORFIVE, PriceRange.FOURORFIVE},
                    new PriceRange[]{PriceRange.ANY},
                    new PriceRange[]{PriceRange.THREE, PriceRange.FOUR, PriceRange.MORETHANFIVE, PriceRange.MORETHANFIVE, PriceRange.MORETHANFIVE}),

            new MarketSetupCard("setup four", R.drawable.market_setup_4,
                    new PriceRange[]{PriceRange.MORETHANFOUR, PriceRange.ANY, PriceRange.ANY},
                    new PriceRange[]{PriceRange.LESSTHANFOUR, PriceRange.MORETHANFOUR, PriceRange.ANY},
                    new PriceRange[]{PriceRange.LESSTHANFIVE, PriceRange.MORETHANFIVE, PriceRange.ANY}),

            new MarketSetupCard("setup five", R.drawable.market_setup_5,
                    new PriceRange[]{PriceRange.TWO, PriceRange.THREE, PriceRange.FOUR, PriceRange.FIVE},
                    new PriceRange[]{PriceRange.ANY},
                    new PriceRange[]{PriceRange.FOUR, PriceRange.FIVE, PriceRange.SIX, PriceRange.MORETHANSIX}),

            new MarketSetupCard("setup six", R.drawable.market_setup_6,
                    new PriceRange[]{PriceRange.THREE, PriceRange.FOUR},
                    new PriceRange[]{PriceRange.LESSTHANFOUR, PriceRange.MORETHANFOUR, PriceRange.ANY},
                    new PriceRange[]{PriceRange.THREEORFOUR, PriceRange.FIVEORSIX, PriceRange.FIVEORSIX, PriceRange.MORETHANSIX})
    };

    public static MarketSetupCard[] getMarketSetupCards() {
        return marketSetupCards;
    }
}
