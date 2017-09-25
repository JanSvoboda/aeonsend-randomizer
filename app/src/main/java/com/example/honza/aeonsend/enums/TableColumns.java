package com.example.honza.aeonsend.enums;

/**
 * Created by honza on 8.9.17.
 */

public enum TableColumns {
    KEY_ID("id"), KEY_NAME("name"), KEY_TYPE("type"), KEY_PRICE("price"), KEY_PICTURE("picture"), KEY_EXPANSION("expansion"), KEY_SETUPDESCRIPTION("setup_description");

    private String value;

    TableColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
