package com.triceratops.dinocityserver.services;

import com.triceratops.dinocityserver.models.Dinosaur;
import com.triceratops.dinocityserver.models.Enclosure;
import com.triceratops.dinocityserver.models.EnclosureTypeResponse;
import com.triceratops.dinocityserver.models.Park;
import com.triceratops.dinocityserver.models.enums.DinoType;
import com.triceratops.dinocityserver.models.enums.SecurityLevel;
import com.triceratops.dinocityserver.models.enums.SizeType;
import com.triceratops.dinocityserver.repositories.EnclosureRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class EnclosureServiceTest {

    private EnclosureService enclosureService;

    @Before
    public void init() {
        enclosureService = new EnclosureService(mock(EnclosureRepository.class));
    }

    @Test
    public void getEnclosuresTypes() {
        EnclosureTypeResponse types = new EnclosureTypeResponse(SizeType.values(), SecurityLevel.values());
        assertEquals(types, enclosureService.getEnclosuresTypes());
    }

    @Test
    public void getRatingOfEnclosureFromDinosaur() {
        Enclosure enclosure = new Enclosure(SizeType.LARGE,SecurityLevel.HIGH, 5);
        Dinosaur dino1 = new Dinosaur(DinoType.TYRANNOSAURUS);
        Dinosaur dino2 = new Dinosaur(DinoType.VELOCIRAPTOR);

        enclosure.addDinosaur(dino1);
        enclosure.addDinosaur(dino2);

        assertEquals(0.73,enclosureService.getRatingOfEnclosureFromDinosaur(enclosure),0.01);

    }
}