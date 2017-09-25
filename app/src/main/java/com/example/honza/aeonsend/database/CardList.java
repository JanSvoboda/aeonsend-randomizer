package com.example.honza.aeonsend.database;

import com.example.honza.aeonsend.cards.CharacterCard;
import com.example.honza.aeonsend.cards.GemCard;
import com.example.honza.aeonsend.cards.NemesisCard;
import com.example.honza.aeonsend.cards.RelicCard;
import com.example.honza.aeonsend.cards.SpellCard;
import com.example.honza.aeonsend.enums.CardType;
import com.example.honza.aeonsend.enums.Expansion;
import com.example.honza.aeonsend.enums.PriceRange;

/**
 * Created by honza on 6.9.17.
 */

public class CardList {
    private static CharacterCard[] basicCharacterCardList = {
            new CharacterCard("Adelheim", CardType.CHARACTER, "character_adelheim", Expansion.BASIC),
            new CharacterCard("Brama", CardType.CHARACTER, "character_brama", Expansion.BASIC),
            new CharacterCard("Jian", CardType.CHARACTER, "character_jian", Expansion.BASIC),
            new CharacterCard("Kadir", CardType.CHARACTER, "character_kadir", Expansion.BASIC),
            new CharacterCard("Lash", CardType.CHARACTER, "character_lash", Expansion.BASIC),
            new CharacterCard("Mist", CardType.CHARACTER, "character_mist", Expansion.BASIC),
            new CharacterCard("Phaedraxa", CardType.CHARACTER, "character_phaedraxa", Expansion.BASIC),
            new CharacterCard("Xaxos", CardType.CHARACTER, "character_xaxos", Expansion.BASIC)
    };

    private static NemesisCard[] basicNemesisCardList = {
            new NemesisCard("Carapace Queen", CardType.NEMESIS, "nemesis_carapacequeen", Expansion.BASIC, ""),
            new NemesisCard("Crooked Mask", CardType.NEMESIS, "nemesis_crookedmask", Expansion.BASIC, ""),
            new NemesisCard("Prince of Gluttons", CardType.NEMESIS, "nemesis_princeofgluttons", Expansion.BASIC, ""),
            new NemesisCard("Rageborn", CardType.NEMESIS, "nemesis_rageborn", Expansion.BASIC, "Shuffle all of the Strike cards together and place them facedown to form the strike deck. Rageborn gains one Fury.")
    };

    private static GemCard[] basicGemCardList = {
            new GemCard("Burning Opal", CardType.GEM, "gem_burningopal", PriceRange.FIVE, Expansion.BASIC),
            new GemCard("Clouded Sapphire", CardType.GEM, "gem_cloudedsapphire", PriceRange.SIX, Expansion.BASIC),
            new GemCard("Diamond Cluster", CardType.GEM, "gem_diamondcluster", PriceRange.FOUR, Expansion.BASIC),
            new GemCard("Jade", CardType.GEM, "gem_jade", PriceRange.TWO, Expansion.BASIC),
            new GemCard("Searing Ruby", CardType.GEM, "gem_searingruby", PriceRange.FOUR, Expansion.BASIC),
            new GemCard("Sifter's Pearl", CardType.GEM, "gem_sifterspearl", PriceRange.THREE, Expansion.BASIC),
            new GemCard("V'riswood Amber", CardType.GEM, "gem_vriswoodamber", PriceRange.THREE, Expansion.BASIC)
    };

    private static RelicCard[] basicRelicCardList = {
            new RelicCard("Blasting Staff", CardType.RELIC, "relic_blastingstaff", PriceRange.FOUR, Expansion.BASIC),
            new RelicCard("Bottled Vortex", CardType.RELIC, "relic_bottledvortex", PriceRange.THREE, Expansion.BASIC),
            new RelicCard("Flexing Dagger", CardType.RELIC, "relic_flexingdagger", PriceRange.TWO, Expansion.BASIC),
            new RelicCard("Focusing Orb", CardType.RELIC, "relic_focusingorb", PriceRange.FOUR, Expansion.BASIC),
            new RelicCard("Mage's Talisman", CardType.RELIC, "relic_magestalisman", PriceRange.FIVE, Expansion.BASIC),
            new RelicCard("Unstable Prism", CardType.RELIC, "relic_unstableprism", PriceRange.THREE, Expansion.BASIC)
    };

    private static SpellCard[] basicSpellCardList = {
            new SpellCard("Amplify Vision", CardType.SPELL, "spell_amplifyvision", PriceRange.FOUR, Expansion.BASIC),
            new SpellCard("Arcane Nexus", CardType.SPELL, "spell_arcanenexus", PriceRange.SEVEN, Expansion.BASIC),
            new SpellCard("Consuming Void", CardType.SPELL, "spell_consumingvoid", PriceRange.SEVEN, Expansion.BASIC),
            new SpellCard("Dark Fire", CardType.SPELL, "spell_darkfire", PriceRange.FIVE, Expansion.BASIC),
            new SpellCard("Essence Theft", CardType.SPELL, "spell_essencetheft", PriceRange.FIVE, Expansion.BASIC),
            new SpellCard("Feral Lightning", CardType.SPELL, "spell_ferallightning", PriceRange.FIVE, Expansion.BASIC),
            new SpellCard("Chaos Arc", CardType.SPELL, "spell_chaosarc", PriceRange.SIX, Expansion.BASIC),
            new SpellCard("Ignite", CardType.SPELL, "spell_ignite", PriceRange.FOUR, Expansion.BASIC),
            new SpellCard("Lavatendril", CardType.SPELL, "spell_lavatendril", PriceRange.FOUR, Expansion.BASIC),
            new SpellCard("Oblivion Swell", CardType.SPELL, "spell_oblivionswell", PriceRange.FIVE, Expansion.BASIC),
            new SpellCard("Phoenix Flame", CardType.SPELL, "spell_phoenixflame", PriceRange.THREE, Expansion.BASIC),
            new SpellCard("Planar Insight", CardType.SPELL, "spell_planarinsight", PriceRange.SIX, Expansion.BASIC),
            new SpellCard("Spectral Echo", CardType.SPELL, "spell_spectralecho", PriceRange.THREE, Expansion.BASIC),
            new SpellCard("Wildfire Whip", CardType.SPELL, "spell_wildfirewhip", PriceRange.SIX, Expansion.BASIC)
    };

    public static CharacterCard[] getBasicCharacterCardList() {
        return basicCharacterCardList;
    }

    public static void setBasicCharacterCardList(CharacterCard[] basicCharacterCardList) {
        CardList.basicCharacterCardList = basicCharacterCardList;
    }

    public static NemesisCard[] getBasicNemesisCardList() {
        return basicNemesisCardList;
    }

    public static void setBasicNemesisCardList(NemesisCard[] basicNemesisCardList) {
        CardList.basicNemesisCardList = basicNemesisCardList;
    }

    public static GemCard[] getBasicGemCardList() {
        return basicGemCardList;
    }

    public static void setBasicGemCardList(GemCard[] basicGemCardList) {
        CardList.basicGemCardList = basicGemCardList;
    }

    public static RelicCard[] getBasicRelicCardList() {
        return basicRelicCardList;
    }

    public static void setBasicRelicCardList(RelicCard[] basicRelicCardList) {
        CardList.basicRelicCardList = basicRelicCardList;
    }

    public static SpellCard[] getBasicSpellCardList() {
        return basicSpellCardList;
    }

    public static void setBasicSpellCardList(SpellCard[] basicSpellCardList) {
        CardList.basicSpellCardList = basicSpellCardList;
    }
}
