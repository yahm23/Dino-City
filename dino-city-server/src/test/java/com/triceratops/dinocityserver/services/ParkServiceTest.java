package com.triceratops.dinocityserver.services;

import com.triceratops.dinocityserver.models.Dinosaur;
import com.triceratops.dinocityserver.models.Enclosure;
import com.triceratops.dinocityserver.models.Park;
import com.triceratops.dinocityserver.models.ParkStats;
import com.triceratops.dinocityserver.models.enums.DinoType;
import com.triceratops.dinocityserver.models.enums.SecurityLevel;
import com.triceratops.dinocityserver.models.enums.SizeType;
import com.triceratops.dinocityserver.repositories.DinosaurRepository;
import com.triceratops.dinocityserver.repositories.EnclosureRepository;
import com.triceratops.dinocityserver.repositories.ParkRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkServiceTest {

    private ParkService parkService;
    private EnclosureService enclosureService;
    private Park park;
    private EnclosureRepository enclosureRepository;
    private ParkRepository parkRepository;
    private DinosaurRepository dinosaurRepository;



    @Before
    public void init() {
        park = buildPark();
        parkRepository = mock(ParkRepository.class);
        when(parkRepository.findParkByName(anyString())).thenReturn(park);
        enclosureRepository = mock(EnclosureRepository.class);
        dinosaurRepository = mock(DinosaurRepository.class);
        enclosureService =mock(EnclosureService.class);
        when(enclosureService.getRatingOfEnclosureFromDinosaur(any(Enclosure.class))).thenReturn(0.18);

        parkService = new ParkService(parkRepository,enclosureRepository, dinosaurRepository, enclosureService);
    }

    @Test
    public void shouldBuildTheCorrectPopulation() {
        ParkStats expectedStats = new ParkStats(1000.0, 10000, 1);

        ParkStats actualStats = parkService.getParkStats("ANYNAME");
//        assertEquals(expectedStats.getMoney(), actualStats.getMoney(), 0.1);
//        assertEquals(expectedStats.getIncome(), actualStats.getIncome(), 0.1);
        assertEquals(expectedStats.getPopulation(), actualStats.getPopulation());
    }

    @Test
    public void shouldBeAbleToBuyAnEnclosure() {
        park.setMoney(10000.00);
        parkService.buyEnclosure("ANYNAME","LARGE","LOW",5);
        assertEquals(0.00,park.getMoney(),0.001);
        assertEquals(2,park.getEnclosures().size());
    }

    @Test
    public void shouldNotBeAbleToBuyAnEnclosure() {
        park.setMoney(1000.00);
        parkService.buyEnclosure("anyName","LARGE","LOW",3);
        assertEquals(1000.00,park.getMoney(),0.001);
        assertEquals(1,park.getEnclosures().size());
    }

    @Test
    public void findCorrectEnclosureWithPosId(){
        park.setMoney(10000.00);
        Enclosure enclosure = parkService.getSpecificEnclosureInParkByPositionId("ANYNAME",5);
        assertEquals(1,enclosure.getDinosaurs().size());
        assertEquals(5,enclosure.getPositionId());
        assertEquals(SecurityLevel.HIGH,enclosure.getSecurityLevel());
        assertEquals(SizeType.LARGE,enclosure.getSize());
    }

    @Test
    public void canAddDinosaurWhenCorrectSecurityAndSize() {
        park.setMoney(10000.0);
        Enclosure enclosure = parkService.getSpecificEnclosureInParkByPositionId("ANYNAME",5);
        boolean result = parkService.addDinosaurToSpecificEnclosure("ANYNAME",5,"TYRANNOSAURUS");
        assertEquals(true, result);
        assertEquals(2, enclosure.getDinosaurs().size());
        assertEquals(7000.0,park.getMoney(),0.01);
    }

    @Test
    public void cantAddDinosaurWhenInCorrectSize() {
        park.setMoney(10000.0);
        Enclosure enclosure = parkService.getSpecificEnclosureInParkByPositionId("ANYNAME",5);
        enclosure.setSize(SizeType.SMALL);
        boolean result = parkService.addDinosaurToSpecificEnclosure("ANYNAME",5,"TYRANNOSAURUS");
        assertEquals(false, result);
        assertEquals(1, enclosure.getDinosaurs().size());
        assertEquals(10000.0,park.getMoney(),0.01);

    }
    @Test
    public void cantAddDinosaurWhenInCorrectSecurity() {
        park.setMoney(10000.0);
        Enclosure enclosure = parkService.getSpecificEnclosureInParkByPositionId("ANYNAME",5);
        enclosure.setSecurityLevel(SecurityLevel.MEDIUM);
        boolean result = parkService.addDinosaurToSpecificEnclosure("ANYNAME",5,"TYRANNOSAURUS");
        assertEquals(false, result);
        assertEquals(1, enclosure.getDinosaurs().size());
        assertEquals(10000.0,park.getMoney(),0.01);

    }

    @Test
    public void cantAddDinosaurWhenInCorrectDiet() {
        park.setMoney(100000.0);
        Enclosure enclosure = parkService.getSpecificEnclosureInParkByPositionId("ANYNAME",5);
        enclosure.setSecurityLevel(SecurityLevel.HIGH);
        parkService.addDinosaurToSpecificEnclosure("ANYNAME",5,"TYRANNOSAURUS");
        boolean result1 = parkService.addDinosaurToSpecificEnclosure("ANYNAME",5,"HOMALOCEPHALE");
        assertFalse(result1);
        assertEquals(2, enclosure.getDinosaurs().size());

    }

    @Test
    public void canGetParkRating(){
        park.setMoney(10000.0);


        double result = parkService.calculateParkRating(park);
        assertEquals(1.18, result, 0.01);

    }

    private Park buildPark() {
        Park park = new Park();
        park.setMoney(1000.0);

        Enclosure enclosure = new Enclosure(SizeType.LARGE,SecurityLevel.HIGH,5);
        enclosure.setPositionId(5);

        enclosure.setSecurityLevel(SecurityLevel.HIGH);
        enclosure.setSize(SizeType.LARGE);

        Dinosaur dino = new Dinosaur(DinoType.VELOCIRAPTOR);
        List<Dinosaur> dinos = new ArrayList<>();
        dinos.add(dino);
        enclosure.setDinosaurs(dinos);
        List<Enclosure> enclosures = new ArrayList<>();
        enclosures.add(enclosure);
        park.setEnclosures(enclosures);
        park.setName("ANYNAME");
        return park;

    }

}