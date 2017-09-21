package com.example.honza.aeonsend.database;

import com.example.honza.aeonsend.enums.CardType;
import com.example.honza.aeonsend.enums.Expansions;

/**
 * Created by honza on 6.9.17.
 */

public class CardList {
    private static String[][] basicCharacterCardList = {
            {"Adelheim", CardType.CHARACTER.getValue(), "0", "character_adelheim.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Brama", CardType.CHARACTER.getValue(), "0", "character_brama.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Jian", CardType.CHARACTER.getValue(), "0", "character_jian.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Kadir", CardType.CHARACTER.getValue(), "0", "character_kadir.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Lash", CardType.CHARACTER.getValue(), "0", "character_lash.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Mist", CardType.CHARACTER.getValue(), "0", "character_mist.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Phaedraxa", CardType.CHARACTER.getValue(), "0", "character_phaedraxa.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Xaxos", CardType.CHARACTER.getValue(), "0", "character_xaxos.jpg", String.valueOf(Expansions.BASIC.getValue())},

    };

    private static String[][] basicNemesisCardList = {
            {"Carapace Queen", CardType.NEMESIS.getValue(), "0", "nemesis_carapacequeen.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Crooked Mask", CardType.NEMESIS.getValue(), "0", "nemesis_crookedmask.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Prince of Gluttons", CardType.NEMESIS.getValue(), "0", "nemesis_princeofgluttons.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Rageborn", CardType.NEMESIS.getValue(), "0", "nemesis_rageborn.jpg", String.valueOf(Expansions.BASIC.getValue())},
    };

    private static String[][] basicGemCardList = {
            {"Burning Opal", CardType.GEM.getValue(), "5", "gem_burningopal.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Clouded Sapphire", CardType.GEM.getValue(), "6", "gem_cloudedsapphire.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Diamond Cluster", CardType.GEM.getValue(), "4", "gem_diamondcluster.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Jade", CardType.GEM.getValue(), "2", "gem_jade.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Searing Ruby", CardType.GEM.getValue(), "4", "gem_searingruby.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Sifter's Pearl", CardType.GEM.getValue(), "3", "gem_sifterspearl.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"V'riswood Amber", CardType.GEM.getValue(), "3", "gem_vriswoodamber.jpg", String.valueOf(Expansions.BASIC.getValue())},
    };

    private static String[][] basicRelicCardList = {
            {"Blasting Staff", CardType.RELIC.getValue(), "4", "relic_blastingstaff.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Bottled Vortex", CardType.RELIC.getValue(), "3", "relic_bottledvortex.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Flexing Dagger", CardType.RELIC.getValue(), "2", "relic_flexingdagger.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Focusing Orb", CardType.RELIC.getValue(), "4", "relic_focusingorb.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Mage's Talisman", CardType.RELIC.getValue(), "5", "relic_magestalisman.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Unstable Prism", CardType.RELIC.getValue(), "3", "relic_unstableprism.jpg", String.valueOf(Expansions.BASIC.getValue())},
    };

    private static String[][] basicSpellCardList = {
            {"Amplify Vision", CardType.SPELL.getValue(), "4", "spell_amplifyvision.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Arcane Nexus", CardType.SPELL.getValue(), "7", "spell_arcanenexus.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Consuming Void", CardType.SPELL.getValue(), "7", "spell_consumingvoid.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Dark Fire", CardType.SPELL.getValue(), "5", "spell_darkfire.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Essence Theft", CardType.SPELL.getValue(), "5", "spell_essencetheft.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Feral Lightning", CardType.SPELL.getValue(), "5", "spell_ferallightning.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Chaos Arc", CardType.SPELL.getValue(), "6", "spell_chaosarc.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Ignite", CardType.SPELL.getValue(), "4", "spell_ignite.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Lavatendril", CardType.SPELL.getValue(), "4", "spell_lavatendril.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Oblivion Swell", CardType.SPELL.getValue(), "5", "spell_oblivionswell.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Phoenix Flame", CardType.SPELL.getValue(), "3", "spell_phoenixflame.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Planar Insight", CardType.SPELL.getValue(), "6", "spell_planarinsight.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Spectral Echo", CardType.SPELL.getValue(), "3", "spell_spectralecho.jpg", String.valueOf(Expansions.BASIC.getValue())},
            {"Wildfire Whip", CardType.SPELL.getValue(), "6", "spell_wildfirewhip.jpg", String.valueOf(Expansions.BASIC.getValue())},
    };

    public static String[][] getBasicCharacterCardList() {
        return basicCharacterCardList;
    }

    public static void setBasicCharacterCardList(String[][] basicCharacterCardList) {
        CardList.basicCharacterCardList = basicCharacterCardList;
    }

    public static String[][] getBasicNemesisCardList() {
        return basicNemesisCardList;
    }

    public static void setBasicNemesisCardList(String[][] basicNemesisCardList) {
        CardList.basicNemesisCardList = basicNemesisCardList;
    }

    public static String[][] getBasicGemCardList() {
        return basicGemCardList;
    }

    public static void setBasicGemCardList(String[][] basicGemCardList) {
        CardList.basicGemCardList = basicGemCardList;
    }

    public static String[][] getBasicRelicCardList() {
        return basicRelicCardList;
    }

    public static void setBasicRelicCardList(String[][] basicRelicCardList) {
        CardList.basicRelicCardList = basicRelicCardList;
    }

    public static String[][] getBasicSpellCardList() {
        return basicSpellCardList;
    }

    public static void setBasicSpellCardList(String[][] basicSpellCardList) {
        CardList.basicSpellCardList = basicSpellCardList;
    }
}
