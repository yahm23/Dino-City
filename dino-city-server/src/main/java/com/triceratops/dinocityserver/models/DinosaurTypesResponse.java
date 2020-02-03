package com.triceratops.dinocityserver.models;

import com.triceratops.dinocityserver.models.enums.DinoType;

import java.util.Arrays;

public class DinosaurTypesResponse {
    private DinoType[] species;

    public DinosaurTypesResponse(DinoType[] species) {
        this.species = species;
    }

    public DinoType[] getSpecies() {
        return species;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DinosaurTypesResponse that = (DinosaurTypesResponse) o;
        return Arrays.equals(species, that.species);
    }



}
