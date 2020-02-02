package com.triceratops.dinocityserver.services;

import com.triceratops.dinocityserver.models.Enclosure;
import com.triceratops.dinocityserver.models.Park;
import com.triceratops.dinocityserver.models.ParkStats;
import com.triceratops.dinocityserver.models.enums.SecurityLevel;
import com.triceratops.dinocityserver.models.enums.SizeType;
import com.triceratops.dinocityserver.repositories.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ParkService {

    private ParkRepository parkRepository;

    @Autowired
    public ParkService(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }

    public Park addPark(String name){
        Park park = new Park();
        park.setMoney(10000);
        park.setEnclosures(new ArrayList<>());
        park.setName(name);
        return parkRepository.save(park);
    }

    public Park getParkByName(String name) {
        return parkRepository.findParkByName(name);
    }

    public ParkStats getParkStats(String name) {
        Park park = parkRepository.findParkByName(name);
        double money = park.getMoney();
        double income = calculateIncome(park);
        int population = calculatePopulation(park);
        return new ParkStats(money, income, population);
    }

    public void buyEnclosure(String name, String size, String security, int positionId){
        Park park = parkRepository.findParkByName(name);
        SizeType enumSize =  SizeType.valueOf(size);
        SecurityLevel enumSecurity =  SecurityLevel.valueOf(security);
        double moneyAvailable = park.getMoney();
        double costOfEnclosure = enumSecurity.getPriceMultiplier() * enumSize.getPrice();

        if(moneyAvailable>= costOfEnclosure){
            double newMoney = moneyAvailable - costOfEnclosure;
            park.setMoney(newMoney);
            Enclosure enclosure = new Enclosure(enumSize,enumSecurity,positionId);
            park.addEnclosure(enclosure);
        }
    }

    private double calculateIncome(Park park) {
        double income = 10 * 1000 * 1;
        return income;
    }

    private int calculatePopulation(Park park) {
        int counter = 0;
        for(Enclosure enclosure: park.getEnclosures()){
            counter += enclosure.getDinosaurs().size();
        }
        return counter;
    }
}
