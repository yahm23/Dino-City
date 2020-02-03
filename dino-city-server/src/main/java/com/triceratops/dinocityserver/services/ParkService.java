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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ParkService {

    private ParkRepository parkRepository;
    private EnclosureRepository enclosureRepository;
    private DinosaurRepository dinosaurRepository;

    @Autowired
    public ParkService(ParkRepository parkRepository, EnclosureRepository enclosureRepository,
                       DinosaurRepository dinosaurRepository) {
        this.parkRepository = parkRepository;
        this.enclosureRepository = enclosureRepository;
        this.dinosaurRepository = dinosaurRepository;
    }

    public boolean addPark(String name){
        Park park = parkRepository.findParkByName(name);
        if (park != null) {
            return false;
        }
        park = new Park();
        park.setMoney(10000);
        park.setEnclosures(new ArrayList<>());
        park.setName(name);
        parkRepository.save(park);
        return true;
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
            park.reduceMoney(costOfEnclosure);
            Enclosure enclosure = new Enclosure(enumSize,enumSecurity,positionId);
            enclosure.setPark(park);
            enclosureRepository.save(enclosure);
            park.addEnclosure(enclosure);
            parkRepository.save(park);
        }
    }

    public Enclosure getSpecificEnclosureInParkByPositionId(String name, int positionId){
        Park park = parkRepository.findParkByName(name);
        for(Enclosure enclosure: park.getEnclosures()){
           if(enclosure.getPositionId() == positionId){
               return enclosure;
           };
        }
        return null;//or null
    }

   public boolean addDinosaurToSpecificEnclosure(String name, int positionId, String dinosaur ){
       Park park =parkRepository.findParkByName(name);
       Enclosure enclosure = getSpecificEnclosureInParkByPositionId(park.getName(),positionId);
       DinoType species = DinoType.valueOf(dinosaur);
       Dinosaur newDino = new Dinosaur(species);
       boolean spaceCheck = isEnoughSpace(enclosure,newDino);
       boolean securityCheck = isEnoughSecurity(enclosure,newDino);
       boolean moneyCheck = isEnoughMoney(park,newDino);

       if(spaceCheck && securityCheck && moneyCheck){

           newDino.setEnclosure(enclosure);
           dinosaurRepository.save(newDino);

           enclosure.addDinosaur(newDino);
           enclosureRepository.save(enclosure);

           park.reduceMoney(newDino.getSpecies().getPrice());
           parkRepository.save(park);
           return true;
       }
       return false;
   }

   private boolean isEnoughSpace(Enclosure enclosure, Dinosaur dinosaur){
       return dinosaur.getSpecies().getSize() <= enclosure.getSize().getSize();

   }

   private boolean isEnoughSecurity(Enclosure enclosure, Dinosaur dinosaur){
       return dinosaur.getSpecies().getThreatLevel().getThreatlevel() <= enclosure.getSecurityLevel().getThreatLevel().getThreatlevel();
   }

   private boolean isEnoughMoney(Park park, Dinosaur dinosaur){
        return dinosaur.getSpecies().getPrice() <= park.getMoney();

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
