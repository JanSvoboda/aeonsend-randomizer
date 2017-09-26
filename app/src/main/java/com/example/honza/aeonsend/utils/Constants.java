package com.example.honza.aeonsend.utils;

import com.example.honza.aeonsend.cards.Card;
import com.example.honza.aeonsend.enums.CardType;

/**
 * Created by honza on 25.9.17.
 */

public class Constants {
    public static final String PACKAGENAME = "com.example.honza.aeonsend";
    public static final String DRAWABLEDEFTYPE = "drawable";

    // Cards table name
    public final static String NEMESISTABLE = CardType.NEMESIS.getValue();
    public final static String CHARACTERTABLE = CardType.CHARACTER.getValue();
    public final static String GEMTABLE = CardType.GEM.getValue();
    public final static String RELICTABLE = CardType.RELIC.getValue();
    public final static String SPELLTABLE = CardType.SPELL.getValue();
    public final static String[] tables = {NEMESISTABLE, CHARACTERTABLE, GEMTABLE, RELICTABLE, SPELLTABLE};
}
