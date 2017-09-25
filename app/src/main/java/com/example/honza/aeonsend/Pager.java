package com.example.honza.aeonsend;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.honza.aeonsend.fragments.CharactersFragment;
import com.example.honza.aeonsend.fragments.SupplyCardsFragment;
import com.example.honza.aeonsend.fragments.NemesisFragment;

/**
 * Created by honza on 21.9.17.
 */

//Extending FragmentStatePagerAdapter
public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
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
                SupplyCardsFragment supplyCardsFragment = new SupplyCardsFragment();
                return supplyCardsFragment;
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