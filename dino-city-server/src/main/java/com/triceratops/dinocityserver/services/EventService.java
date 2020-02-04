package com.triceratops.dinocityserver.services;

import com.triceratops.dinocityserver.events.Events;
import com.triceratops.dinocityserver.models.Dinosaur;
import com.triceratops.dinocityserver.models.Enclosure;
import com.triceratops.dinocityserver.models.EventResponse;
import com.triceratops.dinocityserver.models.Park;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private ParkService parkService;
    private EnclosureService enclosureService;
    private DinoService dinosaurService;

    @Autowired
    public EventService(ParkService parkService, EnclosureService enclosureService, DinoService dinosaurService) {
        this.parkService = parkService;
        this.enclosureService = enclosureService;
        this.dinosaurService = dinosaurService;
    }

    public EventResponse trigger(Park park, Events event) {
        park.reduceMoney(event.getAmount());
        if (event.getKillPercentage() > 0) {
            for (Enclosure enclosure : park.getEnclosures()) {
                for (Dinosaur dinosaur : enclosure.getDinosaurs()) {
                    if (isDinosaurLost(event)) {
                        dinosaurService.removeDinosaur(dinosaur);
                        enclosure.removeDinosaur(dinosaur);
                    }
                }
                enclosureService.updateEnclosure(enclosure);
            }
        }
        return new EventResponse(event.getMessage());
    }

    private boolean isDinosaurLost(Events event) {
        return Math.random() <= event.getKillPercentage();
    }
}
