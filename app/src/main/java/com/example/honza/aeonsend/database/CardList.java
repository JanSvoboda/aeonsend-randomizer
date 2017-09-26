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
    private static CharacterCard[] characterCardList = {
            new CharacterCard("Adelheim", CardType.CHARACTER, "character_adelheim", Expansion.BASIC),
            new CharacterCard("Brama", CardType.CHARACTER, "character_brama", Expansion.BASIC),
            new CharacterCard("Jian", CardType.CHARACTER, "character_jian", Expansion.BASIC),
            new CharacterCard("Kadir", CardType.CHARACTER, "character_kadir", Expansion.BASIC),
            new CharacterCard("Lash", CardType.CHARACTER, "character_lash", Expansion.BASIC),
            new CharacterCard("Malastar", CardType.CHARACTER, "character_malastar", Expansion.NAMELESS),
            new CharacterCard("Mist", CardType.CHARACTER, "character_mist", Expansion.BASIC),
            new CharacterCard("Nym", CardType.CHARACTER, "character_nym", Expansion.DEPTHS),
            new CharacterCard("Phaedraxa", CardType.CHARACTER, "character_phaedraxa", Expansion.BASIC),
            new CharacterCard("Reeve", CardType.CHARACTER, "character_reeve", Expansion.DEPTHS),
            new CharacterCard("Xaxos", CardType.CHARACTER, "character_xaxos", Expansion.BASIC),
            new CharacterCard("Z'Hana", CardType.CHARACTER, "character_zhana", Expansion.DEPTHS)
    };

    private static NemesisCard[] nemesisCardList = {
            new NemesisCard("Blight Lord", CardType.NEMESIS, "nemesis_blightlord", Expansion.NAMELESS, "Add an additional supply pile consisting of a number of Tainted Jades equal to the number of players plus four. Place the Tainted Track next to this mat. " +
                    "Place the Blight Lord token on the first space of the Tainted Track."),
            new NemesisCard("Carapace Queen", CardType.NEMESIS, "nemesis_carapacequeen", Expansion.BASIC, "Place the Husk Track next to this mat, Place two husks into play on thr first two spaces of the Husk Track."),
            new NemesisCard("Crooked Mask", CardType.NEMESIS, "nemesis_crookedmask", Expansion.BASIC, "Shuffle all of the corruption cards together and place them facedown to form the corruption deck."),
            new NemesisCard("Horde-Crone", CardType.NEMESIS, "nemesis_hordecrone", Expansion.DEPTHS, "Shuffle all of the trogg cards together and place them facedown to form the trogg deck. Draw a card from the trogg deck and place it into play"),
            new NemesisCard("Prince of Gluttons", CardType.NEMESIS, "nemesis_princeofgluttons", Expansion.BASIC, "Place one gem from each gem supply, starting with the most expensive, faceup in a pile next to this mat. This pile is the devoured pile."),
            new NemesisCard("Rageborn", CardType.NEMESIS, "nemesis_rageborn", Expansion.BASIC, "Shuffle all of the Strike cards together and place them facedown to form the strike deck. Rageborn gains one Fury."),
            new NemesisCard("Wayward One", CardType.NEMESIS, "nemesis_waywardone", Expansion.NAMELESS, "Place the Wayward One Position Chart next to this mat. Place the Wayward One token on that chart on position I."),
    };

    private static GemCard[] gemCardList = {
            new GemCard("Banishing Topaz", CardType.GEM, "gem_banishingtopaz", PriceRange.FIVE, Expansion.DEPTHS),
            new GemCard("Burning Opal", CardType.GEM, "gem_burningopal", PriceRange.FIVE, Expansion.BASIC),
            new GemCard("Clouded Sapphire", CardType.GEM, "gem_cloudedsapphire", PriceRange.SIX, Expansion.BASIC),
            new GemCard("Diamond Cluster", CardType.GEM, "gem_diamondcluster", PriceRange.FOUR, Expansion.BASIC),
            new GemCard("Jade", CardType.GEM, "gem_jade", PriceRange.TWO, Expansion.BASIC),
            new GemCard("Leeching Agate", CardType.GEM, "gem_leechingagate", PriceRange.THREE, Expansion.NAMELESS),
            new GemCard("Searing Ruby", CardType.GEM, "gem_searingruby", PriceRange.FOUR, Expansion.BASIC),
            new GemCard("Sifter's Pearl", CardType.GEM, "gem_sifterspearl", PriceRange.THREE, Expansion.BASIC),
            new GemCard("V'riswood Amber", CardType.GEM, "gem_vriswoodamber", PriceRange.THREE, Expansion.BASIC)
    };

    private static RelicCard[] relicCardList = {
            new RelicCard("Blasting Staff", CardType.RELIC, "relic_blastingstaff", PriceRange.FOUR, Expansion.BASIC),
            new RelicCard("Bottled Vortex", CardType.RELIC, "relic_bottledvortex", PriceRange.THREE, Expansion.BASIC),
            new RelicCard("Flexing Dagger", CardType.RELIC, "relic_flexingdagger", PriceRange.TWO, Expansion.BASIC),
            new RelicCard("Focusing Orb", CardType.RELIC, "relic_focusingorb", PriceRange.FOUR, Expansion.BASIC),
            new RelicCard("Mage's Talisman", CardType.RELIC, "relic_magestalisman", PriceRange.FIVE, Expansion.BASIC),
            new RelicCard("Molten Hammer", CardType.RELIC, "relic_moltenhammer", PriceRange.FIVE, Expansion.NAMELESS),
            new RelicCard("Temporal Helix", CardType.RELIC, "relic_temporalhelix", PriceRange.SEVEN, Expansion.NAMELESS),
            new RelicCard("Transmogrifier", CardType.RELIC, "relic_transmogrifier", PriceRange.FOUR, Expansion.DEPTHS),
            new RelicCard("Unstable Prism", CardType.RELIC, "relic_unstableprism", PriceRange.THREE, Expansion.BASIC),
            new RelicCard("Vim Dynamo", CardType.RELIC, "relic_unstableprism", PriceRange.FOUR, Expansion.DEPTHS)
    };

    private static SpellCard[] spellCardList = {
            new SpellCard("Amplify Vision", CardType.SPELL, "spell_amplifyvision", PriceRange.FOUR, Expansion.BASIC),
            new SpellCard("Arcane Nexus", CardType.SPELL, "spell_arcanenexus", PriceRange.SEVEN, Expansion.BASIC),
            new SpellCard("Blaze", CardType.SPELL, "spell_blaze", PriceRange.FOUR, Expansion.NAMELESS),
            new SpellCard("Combustion", CardType.SPELL, "spell_combustion", PriceRange.FIVE, Expansion.DEPTHS),
            new SpellCard("Consuming Void", CardType.SPELL, "spell_consumingvoid", PriceRange.SEVEN, Expansion.BASIC),
            new SpellCard("Dark Fire", CardType.SPELL, "spell_darkfire", PriceRange.FIVE, Expansion.BASIC),
            new SpellCard("Devouring Shadow", CardType.SPELL, "spell_devouringshadow", PriceRange.SIX, Expansion.DEPTHS),
            new SpellCard("Disintegrating Scythe", CardType.SPELL, "spell_disintegratingscythe", PriceRange.SEVEN, Expansion.DEPTHS),
            new SpellCard("Essence Theft", CardType.SPELL, "spell_essencetheft", PriceRange.FIVE, Expansion.BASIC),
            new SpellCard("Feral Lightning", CardType.SPELL, "spell_ferallightning", PriceRange.FIVE, Expansion.BASIC),
            new SpellCard("Chaos Arc", CardType.SPELL, "spell_chaosarc", PriceRange.SIX, Expansion.BASIC),
            new SpellCard("Ignite", CardType.SPELL, "spell_ignite", PriceRange.FOUR, Expansion.BASIC),
            new SpellCard("Lavatendril", CardType.SPELL, "spell_lavatendril", PriceRange.FOUR, Expansion.BASIC),
            new SpellCard("Monstrous Inferno", CardType.SPELL, "spell_monstrousinferno", PriceRange.EIGHT, Expansion.DEPTHS),
            new SpellCard("Oblivion Swell", CardType.SPELL, "spell_oblivionswell", PriceRange.FIVE, Expansion.BASIC),
            new SpellCard("Phoenix Flame", CardType.SPELL, "spell_phoenixflame", PriceRange.THREE, Expansion.BASIC),
            new SpellCard("Planar Insight", CardType.SPELL, "spell_planarinsight", PriceRange.SIX, Expansion.BASIC),
            new SpellCard("Radiance", CardType.SPELL, "spell_radiance", PriceRange.EIGHT, Expansion.NAMELESS),
            new SpellCard("Sage's Brand", CardType.SPELL, "spell_sagesbrand", PriceRange.SEVEN, Expansion.NAMELESS),
            new SpellCard("Scrying Bolt", CardType.SPELL, "spell_scryingbolt", PriceRange.SIX, Expansion.NAMELESS),
            new SpellCard("Spectral Echo", CardType.SPELL, "spell_spectralecho", PriceRange.THREE, Expansion.BASIC),
            new SpellCard("Void Bond", CardType.SPELL, "spell_voidbond", PriceRange.FOUR, Expansion.DEPTHS),
            new SpellCard("Wildfire Whip", CardType.SPELL, "spell_wildfirewhip", PriceRange.SIX, Expansion.BASIC)
    };

    public static CharacterCard[] getCharacterCardList() {
        return characterCardList;
    }

    public static void setCharacterCardList(CharacterCard[] characterCardList) {
        CardList.characterCardList = characterCardList;
    }

    public static NemesisCard[] getNemesisCardList() {
        return nemesisCardList;
    }

    public static void setNemesisCardList(NemesisCard[] nemesisCardList) {
        CardList.nemesisCardList = nemesisCardList;
    }

    public static GemCard[] getGemCardList() {
        return gemCardList;
    }

    public static void setGemCardList(GemCard[] gemCardList) {
        CardList.gemCardList = gemCardList;
    }

    public static RelicCard[] getRelicCardList() {
        return relicCardList;
    }

    public static void setRelicCardList(RelicCard[] relicCardList) {
        CardList.relicCardList = relicCardList;
    }

    public static SpellCard[] getSpellCardList() {
        return spellCardList;
    }

    public static void setSpellCardList(SpellCard[] spellCardList) {
        CardList.spellCardList = spellCardList;
    }
}
