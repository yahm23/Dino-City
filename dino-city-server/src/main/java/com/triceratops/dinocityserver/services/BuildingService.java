package com.triceratops.dinocityserver.services;

import com.triceratops.dinocityserver.models.BuildingTypeResponse;
import com.triceratops.dinocityserver.models.enums.BuildingType;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {
    public BuildingTypeResponse getBuildingTypes() {
        return new BuildingTypeResponse(BuildingType.values());
    }
}
