package com.laba.solvd.bankhierarchy.enums;

public enum CardType {

    DEBIT("Debit Card", true),
    CREDIT("Credit Card", true);

    private final String displayName;
    private final boolean isCreditLimitApplicable;

    CardType(String displayName, boolean isCreditLimitApplicable) {
        this.displayName = displayName;
        this.isCreditLimitApplicable = isCreditLimitApplicable;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isCreditLimitApplicable() {
        return isCreditLimitApplicable;
    }
}
