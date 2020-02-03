package com.triceratops.dinocityserver.models.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DietType {
    CARNIVORE("CARNIVORE"),
    HERBIVORE("HERBIVORE"),
    OMNIVORE("OMNIVORE");

    private String name;

    DietType(String name) {
        this.name= name;
    }

    public String getName() {
        return name;
    }
}
