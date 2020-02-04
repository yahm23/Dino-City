package com.triceratops.dinocityserver.controllers;

import com.triceratops.dinocityserver.models.BuildingTypeResponse;
import com.triceratops.dinocityserver.models.EnclosureTypeResponse;
import com.triceratops.dinocityserver.services.BuildingService;
import com.triceratops.dinocityserver.services.EnclosureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buildings")
public class BuildingController {

    private BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService){
        this.buildingService = buildingService;
    }

    @RequestMapping(value="/types",method = RequestMethod.GET)
    public BuildingTypeResponse getBuildingTypes(){
        return buildingService.getBuildingTypes();
    }

}
