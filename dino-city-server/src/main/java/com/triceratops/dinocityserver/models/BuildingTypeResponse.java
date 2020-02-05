package com.triceratops.dinocityserver.models;

import com.triceratops.dinocityserver.models.enums.BuildingType;

import java.util.List;

public class BuildingTypeResponse {
    BuildingType[] types;

    public BuildingTypeResponse(BuildingType[] types) {
        this.types = types;
    }

    public BuildingType[] getTypes() {
        return types;
    }

    public void setTypes(BuildingType[] types) {
        this.types = types;
    }
}
