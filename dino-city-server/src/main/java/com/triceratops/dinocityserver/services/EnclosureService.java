package com.triceratops.dinocityserver.services;

import com.triceratops.dinocityserver.models.Dinosaur;
import com.triceratops.dinocityserver.models.Enclosure;
import com.triceratops.dinocityserver.models.EnclosureTypeResponse;
import com.triceratops.dinocityserver.models.enums.SecurityLevel;
import com.triceratops.dinocityserver.models.enums.SizeType;
import com.triceratops.dinocityserver.repositories.EnclosureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EnclosureService {

    private EnclosureRepository enclosureRepository;

    @Autowired
    public EnclosureService(EnclosureRepository enclosureRepository) {
        this.enclosureRepository = enclosureRepository;
    }

    public EnclosureTypeResponse getEnclosuresTypes() {
        return new EnclosureTypeResponse(SizeType.values(), SecurityLevel.values());
    }

    public double calculateCosts(Enclosure enclosure) {
        double securityCost = enclosure.getSecurityLevel().getThreatLevel().getThreatLevel() * 40;
        double sizeCost = enclosure.getSize().getSize() * 20;
        double amount = securityCost + sizeCost;
        for (Dinosaur dino : enclosure.getDinosaurs()) {
            double dinoFeedingCost = dino.getSpecies().getPrice() * 0.1;
            amount += dinoFeedingCost;
        }
        return amount;
    }

    public double getRating(Enclosure enclosure){
        double rating = 0.0;
        for(Dinosaur dino: enclosure.getDinosaurs()){
            double sizeRating = dino.getSpecies().getSize()*0.01;
            double threatRating = dino.getSpecies().getThreatLevel().getThreatLevel()*0.06;
            rating += sizeRating+threatRating;
        }
        return rating;
    }

    public void updateEnclosure(Enclosure enclosure) {
        enclosureRepository.save(enclosure);
    }
}
