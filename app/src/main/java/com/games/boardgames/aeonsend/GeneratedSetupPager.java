package com.games.boardgames.aeonsend;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.games.boardgames.aeonsend.fragments.CharactersFragment;
import com.games.boardgames.aeonsend.fragments.MarketFragment;
import com.games.boardgames.aeonsend.fragments.NemesisFragment;

/**
 * Created by honza on 21.9.17.
 */

//Extending FragmentStatePagerAdapter
public class GeneratedSetupPager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public GeneratedSetupPager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount = tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                NemesisFragment nemesisFragment = new NemesisFragment();
                return nemesisFragment;
            case 1:
                CharactersFragment charactersFragment = new CharactersFragment();
                return charactersFragment;
            case 2:
                MarketFragment marketFragment = new MarketFragment();
                return marketFragment;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}