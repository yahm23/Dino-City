package com.triceratops.dinocityserver.models.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SecurityLevel {
    HIGH("HIGH",1.6, ThreatLevel.HIGH),
    MEDIUM("MEDIUM",1.3, ThreatLevel.MEDIUM),
    LOW("LOW",1.0,ThreatLevel.LOW);

    private final double priceMultiplier;
    private ThreatLevel threatLevel = null;
    private String name;


    SecurityLevel(String name, double priceMultiplier, ThreatLevel threatLevel) {
        this.priceMultiplier = priceMultiplier;
        this.threatLevel = threatLevel;
        this.name =name;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }
    public ThreatLevel getThreatLevel() {
        return threatLevel;
    }

    public String getName() {
        return name;
    }
}
