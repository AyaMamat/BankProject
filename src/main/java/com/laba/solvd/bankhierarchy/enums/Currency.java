package com.laba.solvd.bankhierarchy.enums;

public enum Currency {
    USD("United States Dollar", "$", "USA"),
    EUR("Euro", "€", "Europe"),
    GBP("British Pound Sterling", "£", "United Kingdom");

    private final String displayName;
    private final String symbol;
    private final String country;

    Currency(String displayName, String symbol, String country) {
        this.displayName = displayName;
        this.symbol = symbol;
        this.country = country;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCountry() {
        return country;
    }

}
