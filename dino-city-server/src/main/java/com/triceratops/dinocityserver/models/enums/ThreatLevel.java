package com.triceratops.dinocityserver.models.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ThreatLevel {
    HIGH("HIGH",5),
    MEDIUM("MEDIUM",3),
    LOW("LOW",2);

    private final int threatlevel;

    private String name;

    ThreatLevel(String name, int threatLevel) {
        this.threatlevel = threatLevel;
        this.name =name;
    }

    public int getThreatlevel() {
        return threatlevel;
    }

    public String getName() {
        return name;
    }
}
