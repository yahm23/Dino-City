package com.triceratops.dinocityserver.services;

import com.triceratops.dinocityserver.models.Dinosaur;
import com.triceratops.dinocityserver.models.Enclosure;
import com.triceratops.dinocityserver.models.Park;
import com.triceratops.dinocityserver.models.ParkStats;
import com.triceratops.dinocityserver.models.enums.SecurityLevel;
import com.triceratops.dinocityserver.models.enums.SizeType;
import com.triceratops.dinocityserver.repositories.EnclosureRepository;
import com.triceratops.dinocityserver.repositories.ParkRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkServiceTest {

    private ParkService parkService;
    private EnclosureService enclosureService;
    private Park park;
    private EnclosureRepository enclosureRepository;
    private ParkRepository parkRepository;

    @Before
    public void init() {
        park = buildPark();
        parkRepository = mock(ParkRepository.class);
        when(parkRepository.findParkByName(anyString())).thenReturn(park);
        enclosureRepository = mock(EnclosureRepository.class);

        parkService = new ParkService(parkRepository,enclosureRepository);
    }

    @Test
    public void shouldBuildTheCorrectPopulation() {
        ParkStats expectedStats = new ParkStats(1000.0, 10000, 2);
        ParkStats actualStats = parkService.getParkStats("anyName");
        assertEquals(expectedStats.getMoney(), actualStats.getMoney(), 0.1);
        assertEquals(expectedStats.getIncome(), actualStats.getIncome(), 0.1);
        assertEquals(expectedStats.getPopulation(), actualStats.getPopulation());
    }

    @Test
    public void shouldBeAbleToBuyAnEnclosure() {
        park.setMoney(10000.00);
        parkService.buyEnclosure("ANYNAME","LARGE","LOW",5);
        assertEquals(7000.00,park.getMoney(),0.001);
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
        parkService.buyEnclosure("ANYNAME","LARGE","LOW",5);
        Enclosure enclosure = parkService.getSpecificEnclosureInParkByPositionId("ANYNAME",5);
        assertEquals(0,enclosure.getDinosaurs().size());
        assertEquals(5,enclosure.getPositionId());
        assertEquals(SecurityLevel.LOW,enclosure.getSecurityLevel());
        assertEquals(SizeType.LARGE,enclosure.getSize());
    }

    private Park buildPark() {
        Park park = new Park();
        park.setMoney(1000.0);
        Enclosure enclosure = new Enclosure();
        Dinosaur dino = new Dinosaur();
        List<Dinosaur> dinos = new ArrayList<>();
        dinos.add(dino);
        dinos.add(dino);
        enclosure.setDinosaurs(dinos);
        List<Enclosure> enclosures = new ArrayList<>();
        enclosures.add(enclosure);
        park.setEnclosures(enclosures);
        return park;
    }


}