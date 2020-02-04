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


    public double getRatingOfEnclosureFromDinosaur(Enclosure enclosure){
        double rating = 0.0;
        for(Dinosaur dino: enclosure.getDinosaurs()){
            double sizeRating = dino.getSpecies().getSize()*0.01;
            double threatRating = dino.getSpecies().getThreatLevel().getThreatLevel()*0.06;
            rating += sizeRating+threatRating;
        }
        return rating;
    }
}
//    public void addEnclosure(SizeType sizeType, SecurityLevel securityLevel, int positionId ){
//        Enclosure enclosure = new Enclosure(sizeType,securityLevel,positionId);
//        enclosureRepository.save(enclosure);
//    }

//    public void addDinoToEnclosure(int positionId, String dinosaur ){};
