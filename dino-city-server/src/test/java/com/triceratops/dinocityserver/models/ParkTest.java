package com.triceratops.dinocityserver.models;

import com.triceratops.dinocityserver.repositories.ParkRepository;
import com.triceratops.dinocityserver.services.ParkService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkTest {

    private ParkService parkService;
    private Park park;
    private ParkRepository parkRepository;

    @Before
    public void init() {
        park = buildPark();
        parkRepository = mock(ParkRepository.class);
        when(parkRepository.findParkByName(anyString())).thenReturn(park);
        parkService = new ParkService(parkRepository);
    }


    @Test
    public void calculateIncome() {
        double expected =10.00 ;
        assertEquals(expected, park.calculateIncome(),0.01);
    }

    @Test
    public void calculatePopulation() {
        int expected = 2;
        assertEquals(expected,park.calculatePopulation());

    }

    private Park buildPark() {
        Park park = new Park();
        park.setMoney(10010.00);
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