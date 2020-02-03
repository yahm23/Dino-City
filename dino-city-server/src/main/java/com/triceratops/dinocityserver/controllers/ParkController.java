package com.triceratops.dinocityserver.controllers;

import com.triceratops.dinocityserver.models.Enclosure;
import com.triceratops.dinocityserver.models.Park;
import com.triceratops.dinocityserver.models.ParkStats;
import com.triceratops.dinocityserver.services.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/park")
public class ParkController {

    private ParkService parkService;

    @Autowired
    public ParkController(ParkService parkService) {
        this.parkService = parkService;
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public Park getPark(@PathVariable String name) {
        return parkService.getParkByName(name);
    }

    @RequestMapping(value = "/stats/name/{name}", method = RequestMethod.GET)
    public ParkStats getParkStats(@PathVariable String name) {
        return parkService.getParkStats(name);
    }

    @RequestMapping(value="/new/{name}",method = RequestMethod.GET)
    public boolean addPark(@PathVariable String name){
        return parkService.addPark(name);
    }

    @RequestMapping(value="/enclosure/{name}/{size}/{security}/{positionId}", method = RequestMethod.POST )
    public void addEnclosureToPark(@PathVariable String name, @PathVariable String size, @PathVariable String security, @PathVariable int positionId){
        parkService.buyEnclosure(name,size,security,positionId);
    }

    @RequestMapping(value="/positionId/{name}/{positionId}",method = RequestMethod.GET)
    public Enclosure findSpecificEnclosureInPark(@PathVariable String name, @PathVariable int positionId){
        return parkService.getSpecificEnclosureInParkByPositionId(name,positionId);
    }

    @RequestMapping(value="/addDinosaurToSpecificEnclosure/{name}/{positionId}/{dinosaur}",method = RequestMethod.GET)
    public boolean findSpecificEnclosureInPark(@PathVariable String name, @PathVariable int positionId, @PathVariable String dinosaur){
        return parkService.addDinosaurToSpecificEnclosure(name,positionId,dinosaur);
    }
}
