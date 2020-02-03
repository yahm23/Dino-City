package com.triceratops.dinocityserver.controllers;

import com.triceratops.dinocityserver.models.Dinosaur;
import com.triceratops.dinocityserver.models.DinosaurTypesResponse;
import com.triceratops.dinocityserver.services.DinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dinosaur")
public class DinosaurController {
    private DinoService dinoService;

    @Autowired
    public DinosaurController(DinoService dinoService){
        this.dinoService =dinoService;
    }

    @RequestMapping(value="/species", method = RequestMethod.GET)
    public DinosaurTypesResponse getAllSpecies(){
        return dinoService.getDinoTypes();
    }
}
