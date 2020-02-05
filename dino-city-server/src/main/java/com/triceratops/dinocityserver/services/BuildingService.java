package com.triceratops.dinocityserver.services;

import com.triceratops.dinocityserver.models.Building;
import com.triceratops.dinocityserver.models.BuildingTypeResponse;
import com.triceratops.dinocityserver.models.enums.BuildingType;
import com.triceratops.dinocityserver.repositories.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {

    private BuildingRepository buildingRepository;

    @Autowired
    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    public BuildingTypeResponse getBuildingTypes() {
        return new BuildingTypeResponse(BuildingType.values());
    }

    public void save(Building building) {
        buildingRepository.save(building);
    }

    public double getRating(Building building) {
        return building.getBuildingType().getReputationMultiplier();
    }

    public double calculateCosts(Building building) {
        return building.getBuildingType().getCost();
    }

    public void remove(Building foundBuilding) {
        buildingRepository.delete(foundBuilding);
    }
}
