package com.triceratops.dinocityserver.models.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ThreatLevel {
    HIGH("HIGH",5),
    MEDIUM("MEDIUM",3),
    LOW("LOW",2);

    private int threatLevel;

    private String name;

    ThreatLevel(String name, int threatLevel) {
        this.threatLevel = threatLevel;
        this.name =name;
    }

    public int getThreatLevel() {
        return threatLevel;
    }

    public String getName() {
        return name;
    }
}
