package com.example.honza.aeonsend.cards;

import com.example.honza.aeonsend.enums.CardType;
import com.example.honza.aeonsend.enums.Expansion;
import com.example.honza.aeonsend.enums.PriceRange;

/**
 * Created by honza on 3.9.17.
 */

public class NemesisCard extends Card {

    private String setupDescription;

    public NemesisCard(int id, String name, CardType type, String picture, Expansion expansion, String setupDescription) {
        super(id, name, type, picture, expansion);
        setType(CardType.NEMESIS);
        setSetupDescription(setupDescription);
    }

    public NemesisCard(String name, CardType type, String picture, Expansion expansion, String setupDescription) {
        super(name, type, picture, expansion);
        setType(CardType.NEMESIS);
        setSetupDescription(setupDescription);
    }

    public String getSetupDescription() {
        return setupDescription;
    }

    public void setSetupDescription(String setupDescription) {
        this.setupDescription = setupDescription;
    }
}
