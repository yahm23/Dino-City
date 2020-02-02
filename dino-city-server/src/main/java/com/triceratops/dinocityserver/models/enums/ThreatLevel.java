package com.triceratops.dinocityserver.models.enums;

public enum ThreatLevel {
    HIGH(5),
    MEDIUM(3),
    LOW(2);

    private final int threatlevel;

    ThreatLevel(int threatLevel) {
        this.threatlevel = threatLevel;
    }

    public int getThreatlevel() {
        return threatlevel;
    }
}
