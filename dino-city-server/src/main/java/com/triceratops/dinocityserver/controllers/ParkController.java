package com.triceratops.dinocityserver.controllers;

import com.triceratops.dinocityserver.models.Enclosure;
import com.triceratops.dinocityserver.models.EventResponse;
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

    @RequestMapping(value = "/delete/name/{name}", method = RequestMethod.DELETE)
    public void deletePark(@PathVariable String name) {
         parkService.deletePark(name);
    }

    @RequestMapping(value = "/stats/name/{name}", method = RequestMethod.GET)
    public ParkStats getParkStats(@PathVariable String name) {
        return parkService.getParkStats(name);
    }

    @RequestMapping(value="/new/{name}",method = RequestMethod.POST)
    public boolean addPark(@PathVariable String name){
        return parkService.addPark(name);
    }

    @RequestMapping(value="/enclosure/{name}/{size}/{security}/{positionId}", method = RequestMethod.POST )
    public void addEnclosureToPark(@PathVariable String name, @PathVariable String size, @PathVariable String security, @PathVariable int positionId){
        parkService.buyEnclosure(name,size,security,positionId);
    }

    @RequestMapping(value="/name/{name}/enclosure/{positionId}/dinosaur/{dinosaur}",method = RequestMethod.POST)
    public boolean addDinosaurToSpecificEnclosure(@PathVariable String name, @PathVariable int positionId, @PathVariable String dinosaur){
        return parkService.addDinosaurToSpecificEnclosure(name,positionId,dinosaur);
    }

    @RequestMapping(value="/name/{parkName}/event",method = RequestMethod.GET)
    public EventResponse getRandomEvent(@PathVariable String parkName) {
        return parkService.triggerEvent(parkName);
    }

    @RequestMapping(value="/name/{parkName}/dinosaur/delete/{id}",method = RequestMethod.DELETE)
    public void sellDino(@PathVariable String parkName, @PathVariable int id){
         parkService.sellDino(parkName, (long) id);
    }

    @RequestMapping(value="/name/{parkName}/enclosure/{positionId}/upgrade/size/{size}", method=RequestMethod.PUT)
    public boolean upgradeSize(@PathVariable String parkName, @PathVariable int positionId,@PathVariable String size){
            return parkService.upgradeSize(parkName,positionId,size);
     }

    @RequestMapping(value="/name/{parkName}/enclosure/{positionId}/upgrade/security/{security}", method=
            RequestMethod.PUT)
    public boolean upgradeSecurity(@PathVariable String parkName, @PathVariable int positionId, @PathVariable String security){
            return parkService.upgradeSecurity(parkName,positionId,security);
     }

    @RequestMapping(value="/name/{name}/building/buy/{type}/{positionId}", method = RequestMethod.POST )
    public void addBuildingToPark(@PathVariable String name, @PathVariable String type, @PathVariable int positionId){
        parkService.buyBuilding(name,type,positionId);
    }

    @RequestMapping(value="/name/{name}/building/sell/{positionId}", method = RequestMethod.DELETE )
    public void sellBuildingFromPark(@PathVariable String name, @PathVariable int positionId){
        parkService.sellBuilding(name,positionId);
    }
}
