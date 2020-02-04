package com.triceratops.dinocityserver.services;

import com.triceratops.dinocityserver.models.Dinosaur;
import com.triceratops.dinocityserver.models.DinosaurTypesResponse;
import com.triceratops.dinocityserver.models.EnclosureTypeResponse;
import com.triceratops.dinocityserver.models.enums.DinoType;
import com.triceratops.dinocityserver.models.enums.SecurityLevel;
import com.triceratops.dinocityserver.models.enums.SizeType;
import com.triceratops.dinocityserver.repositories.DinosaurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DinoService {
    private DinosaurRepository dinosaurRepository;

    @Autowired
    public DinoService(DinosaurRepository dinosaurRepository){
        this.dinosaurRepository = dinosaurRepository;
    }

    public DinosaurTypesResponse getDinoTypes() {
        return new DinosaurTypesResponse(DinoType.values());
    }


    public void removeDinosaur(Dinosaur dinosaur) {
        dinosaurRepository.delete(dinosaur);
    }
}
