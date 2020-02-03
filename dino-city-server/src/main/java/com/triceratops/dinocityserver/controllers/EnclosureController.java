package com.triceratops.dinocityserver.controllers;

import com.triceratops.dinocityserver.models.Enclosure;
import com.triceratops.dinocityserver.models.EnclosureTypeResponse;
import com.triceratops.dinocityserver.services.EnclosureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enclosures")
public class EnclosureController {

    private EnclosureService enclosureService;

    @Autowired
    public EnclosureController(EnclosureService enclosureService){
        this.enclosureService = enclosureService;
    }

    @RequestMapping(value="/types",method = RequestMethod.GET)
    public EnclosureTypeResponse getEnclosureTypes(){
        return enclosureService.getEnclosuresTypes();
    }

}
