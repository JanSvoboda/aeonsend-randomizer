package com.games.boardgames.aeonsend.utils;

import com.games.boardgames.aeonsend.enums.CardType;

/**
 * Created by honza on 25.9.17.
 */

public class Constants {
    public static final String PACKAGENAME = "com.games.boardgames.aeonsend";
    public static final String DRAWABLEDEFTYPE = "drawable";

    // Cards table name
    public final static String EXPANSIONTABLE = CardType.EXPANSION.getValue();
    public final static String NEMESISTABLE = CardType.NEMESIS.getValue();
    public final static String CHARACTERTABLE = CardType.CHARACTER.getValue();
    public final static String GEMTABLE = CardType.GEM.getValue();
    public final static String RELICTABLE = CardType.RELIC.getValue();
    public final static String SPELLTABLE = CardType.SPELL.getValue();
    public final static String[] tables = {EXPANSIONTABLE, NEMESISTABLE, CHARACTERTABLE, GEMTABLE, RELICTABLE, SPELLTABLE};

    // Extras names
    public final static String EXTRASNUMPLAYERS = "numPlayers";
    public final static String EXTRASCHOSENSETUP = "chosenSetup";
    public final static String EXTRASSELECTEDEXPANSION = "selectedExpansions";

}
