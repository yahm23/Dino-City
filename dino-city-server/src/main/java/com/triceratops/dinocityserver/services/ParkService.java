package com.triceratops.dinocityserver.services;

import com.triceratops.dinocityserver.models.Park;
import com.triceratops.dinocityserver.models.ParkStats;
import com.triceratops.dinocityserver.repositories.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkService {

    private ParkRepository parkRepository;

    @Autowired
    public ParkService(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }

    public Park getParkByName(String name) {
        return parkRepository.findParkByName(name);
    }

    public ParkStats getParkStats(String name) {
        Park park = parkRepository.findParkByName(name);
        double money = park.getMoney();
        double income = park.calculateIncome();
        int population = park.calculatePopulation();
        return new ParkStats(money, income, population);
    }
}
