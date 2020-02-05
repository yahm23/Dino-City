package com.triceratops.dinocityserver.models.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SecurityLevel {
    LOW("LOW",1.0,ThreatLevel.LOW),
    MEDIUM("MEDIUM",1.3, ThreatLevel.MEDIUM),
    HIGH("HIGH",1.7, ThreatLevel.HIGH);

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
