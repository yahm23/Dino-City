package com.triceratops.dinocityserver.models.enums;

public enum SecurityLevel {
    HIGH(1.6, ThreatLevel.HIGH),
    MEDIUM(1.3, ThreatLevel.MEDIUM),
    LOW(1.0,ThreatLevel.LOW);

    private final double priceMultiplier;
    private ThreatLevel threatLevel = null;

    SecurityLevel(double priceMultiplier, ThreatLevel threatLevel) {
        this.priceMultiplier = priceMultiplier;
        this.threatLevel = threatLevel;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public ThreatLevel getThreatLevel() {
        return threatLevel;
    }
}
