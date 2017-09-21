package com.example.honza.aeonsend.cards;

import android.util.Log;

import com.example.honza.aeonsend.enums.PriceRange;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by honza on 14.9.17.
 */

public class MarketCard {
    private String name;
    private int image;
    private int numberOfGems;
    private int numberofRelics;
    private int numberOfSpells;

    private PriceRange[] gemsPriceList;
    private PriceRange[] relicsPriceList;
    private PriceRange[] spellsPriceList;

    public MarketCard(String name, int image, PriceRange[] gemsPriceList,
                      PriceRange[] relicsPriceList, PriceRange[] spellsPriceList) {
        this.name = name;
        this.image = image;
        this.gemsPriceList = gemsPriceList;
        this.relicsPriceList = relicsPriceList;
        this.spellsPriceList = spellsPriceList;
        this.numberOfGems = getNumberOfGems();
        this.numberofRelics = getNumberofRelics();
        this.numberOfSpells = getNumberOfSpells();

        if (numberOfGems + numberofRelics + numberOfSpells != 9) {
            Log.d("CreateMarketCard", "MarketCard: " + name + ", has wrong number of Supply Cards." +
                    " Number of provided supply cards is: " + numberOfGems + numberofRelics + numberOfSpells);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getNumberOfGems() {
        Log.d("#GEMS", "getNumberOfGems: " + gemsPriceList.toString());
        return gemsPriceList.length;
    }

    public void setNumberOfGems(int numberOfGems) {
        this.numberOfGems = numberOfGems;
    }

    public int getNumberofRelics() {
        return relicsPriceList.length;
    }

    public void setNumberofRelics(int numberofRelics) {
        this.numberofRelics = numberofRelics;
    }

    public int getNumberOfSpells() {
        return spellsPriceList.length;
    }

    public void setNumberOfSpells(int numberOfSpells) {
        this.numberOfSpells = numberOfSpells;
    }

    public PriceRange[] getGemsPriceList() {
        return gemsPriceList;
    }

    public void setGemsPriceList(PriceRange[] gemsPriceList) {
        this.gemsPriceList = gemsPriceList;
    }

    public PriceRange[] getRelicsPriceList() {
        return relicsPriceList;
    }

    public void setRelicsPriceList(PriceRange[] relicsPriceList) {
        this.relicsPriceList = relicsPriceList;
    }

    public PriceRange[] getSpellsPriceList() {
        return spellsPriceList;
    }

    public void setSpellsPriceList(PriceRange[] spellsPriceList) {
        this.spellsPriceList = spellsPriceList;
    }

    public static HashMap<PriceRange, Integer> mapPriceRangeFromArray(PriceRange[] priceRanges) {
        HashMap<PriceRange, Integer> mapPriceRange = new LinkedHashMap<>();
        int i = 0;

        for (PriceRange priceRange : priceRanges) {
            if (!(mapPriceRange.get(priceRange) == null)) {
                i = mapPriceRange.get(priceRange);
            } else {
                i = 0;
            }

            mapPriceRange.put(priceRange, ++i);
        }

        return mapPriceRange;
    }

    public static String toStringPriceRange(HashMap<PriceRange, Integer> mapPriceRange) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry me: mapPriceRange.entrySet()) {
            sb.append(me.getValue() + "x " + me.getKey().toString() + "  |  ");
        }
        return sb.toString();
    }
}
