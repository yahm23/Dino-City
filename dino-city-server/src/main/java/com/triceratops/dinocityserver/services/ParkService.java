package com.triceratops.dinocityserver.services;

import com.triceratops.dinocityserver.models.*;
import com.triceratops.dinocityserver.models.enums.BuildingType;
import com.triceratops.dinocityserver.events.Events;
import com.triceratops.dinocityserver.models.enums.DinoType;
import com.triceratops.dinocityserver.models.enums.SecurityLevel;
import com.triceratops.dinocityserver.models.enums.SizeType;
import com.triceratops.dinocityserver.repositories.DinosaurRepository;
import com.triceratops.dinocityserver.repositories.EnclosureRepository;
import com.triceratops.dinocityserver.repositories.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.triceratops.dinocityserver.utils.Utils.round;


@Service
public class ParkService {

    private ParkRepository parkRepository;
    private EnclosureRepository enclosureRepository;
    private DinosaurRepository dinosaurRepository;
    private BuildingService buildingService;
    private EventService eventService;
    private EnclosureService enclosureService;

    @Autowired
    public ParkService(ParkRepository parkRepository, EnclosureRepository enclosureRepository,
                       DinosaurRepository dinosaurRepository, BuildingService buildingService,
                       EventService eventService, EnclosureService enclosureService) {
        this.parkRepository = parkRepository;
        this.enclosureRepository = enclosureRepository;
        this.dinosaurRepository = dinosaurRepository;
        this.eventService = eventService;
        this.enclosureService =enclosureService;
        this.buildingService = buildingService;
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
        double cost = calculateCosts(park);
        double rating = calculateParkRating(park);

        return new ParkStats(money, income, population, cost, rating);
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
        return null;
    }

    public void updateAllParks(){
        for(Park park: parkRepository.findAll()){
            double income = this.calculateIncome(park);
            double cost = this.calculateCosts(park);
            park.addMoney(income - cost);
            double rating= calculateParkRating(park);
            park.setRating(rating);
            parkRepository.save(park);
        }
    }

    public double calculateCosts(Park park) {
        double amount = 0;
        for (Enclosure enclosure : park.getEnclosures()) {
            amount += enclosureService.calculateCosts(enclosure);
        }
        for (Building building : park.getBuildings()) {
            amount += buildingService.calculateCosts(building);
        }
        return round(amount);
    }

    public double calculateParkRating(Park park){
        double rating =1.0;
        for(Enclosure enclosure: park.getEnclosures()){
            rating += enclosureService.getRating(enclosure);
        }
        for(Building building: park.getBuildings()){
            rating += buildingService.getRating(building);
        }
        return round(rating);
    }

   public boolean addDinosaurToSpecificEnclosure(String name, int positionId, String dinosaur ){
       Park park =parkRepository.findParkByName(name);
       Enclosure enclosure = getSpecificEnclosureInParkByPositionId(park.getName(),positionId);
       DinoType species = DinoType.valueOf(dinosaur);
       Dinosaur newDino = new Dinosaur(species);
       boolean spaceCheck = isEnoughSpace(enclosure,newDino);
       boolean securityCheck = isEnoughSecurity(enclosure,newDino);
       boolean moneyCheck = isEnoughMoney(park,newDino);
       boolean dietCheck = isCorrectDinoTypes(enclosure,newDino);
       if(spaceCheck && securityCheck && moneyCheck && dietCheck){
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

   private boolean isCorrectDinoTypes(Enclosure enclosure,Dinosaur dinosaurToAdd ){
       if(dinosaurToAdd.getSpecies().getDietType().getName() =="HERBIVORE"){
            for(Dinosaur dinosaur: enclosure.getDinosaurs()){
                String name = dinosaur.getSpecies().getDietType().getName();
                if ( name == "OMNIVORE" || name =="CARNIVORE" ){
                    return false;
                }
            }
        }

       if(dinosaurToAdd.getSpecies().getDietType().getName() =="CARNIVORE"||dinosaurToAdd.getSpecies().getDietType().getName() =="OMNIVORE"){
            for(Dinosaur dinosaur: enclosure.getDinosaurs()){
                String name = dinosaur.getSpecies().getDietType().getName();
                if ( name == "HERBIVORE" ){
                    return false;
                }
            }
        }
       return true;
   }

   private boolean isEnoughSecurity(Enclosure enclosure, Dinosaur dinosaur){
       return dinosaur.getSpecies().getThreatLevel().getThreatLevel() <= enclosure.getSecurityLevel().getThreatLevel().getThreatLevel();
   }

   private boolean isEnoughMoney(Park park, Dinosaur dinosaur){
        return dinosaur.getSpecies().getPrice() <= park.getMoney();

   }

    private double calculateIncome(Park park) {
        double income = 300 * this.calculateParkRating(park);
        if(income==300){
            return 0.0;
        }
        return round(income);
    }

    public int calculatePopulation(Park park) {
        int counter = 0;
        for(Enclosure enclosure: park.getEnclosures()){
            counter += enclosure.getDinosaurs().size();
        }
        return counter;
    }

    public EventResponse triggerEvent(String parkName) {
        Park park = parkRepository.findParkByName(parkName);
        int randomEvent = (int)(Events.values().length * Math.random());
        Events event = Events.values()[randomEvent];
        if (Math.random() <= event.getEventChance()) {
            EventResponse response = eventService.trigger(park, event);
            parkRepository.save(park);
            return response;
        }
        return new EventResponse("");
    }

    public boolean upgradeSize(String name, int positionId, String size){
        Park park = parkRepository.findParkByName(name);
        for(Enclosure enclosure: park.getEnclosures()){
            if(enclosure.getPositionId()==positionId){
                SizeType sizeType = SizeType.valueOf(size);
                double initialCost = enclosure.getSize().getPrice();
                double newSizeCost = sizeType.getPrice();
                double upgradeCost = round((newSizeCost-initialCost)*1.1);
                if(park.getMoney()>= upgradeCost){
                    park.reduceMoney(upgradeCost);
                    parkRepository.save(park);
                    enclosure.setSize(sizeType);
                    enclosureRepository.save(enclosure);
                    return true;
                }
            }
        }
        return false;
    }

    public void deletePark(String name){
        Park park = parkRepository.findParkByName(name);
        parkRepository.delete(park);
    }

    public boolean upgradeSecurity(String name, int positionId, String security){
        Park park = parkRepository.findParkByName(name);
        for(Enclosure enclosure: park.getEnclosures()){
            if(enclosure.getPositionId()==positionId){
                SecurityLevel securityType = SecurityLevel.valueOf(security);

                double initialCost = enclosure.getSecurityLevel().getPriceMultiplier();
                double newSizeCost = securityType.getPriceMultiplier();
                double upgradeCost = round((newSizeCost-initialCost+1)*600);
                if(park.getMoney()>= upgradeCost){
                    park.reduceMoney(upgradeCost);
                    parkRepository.save(park);
                    enclosure.setSecurityLevel(securityType);
                    enclosureRepository.save(enclosure);
                    return true;
                }
            }
        }
        return false;
    }

    public void buyBuilding(String name, String type, int positionId) {
        Park park = parkRepository.findParkByName(name);
        BuildingType buildingType =  BuildingType.valueOf(type);
        if(park.getMoney() >= buildingType.getCost()){
            park.reduceMoney(buildingType.getCost());
            Building building = new Building(buildingType, park, positionId);
            buildingService.save(building);
            park.addBulding(building);
            parkRepository.save(park);
        }
    }

    public void sellBuilding(String name, int positionId) {
        Park park = parkRepository.findParkByName(name);
        Building foundBuilding = null;
        for (Building building : park.getBuildings()) {
            if (building.getPositionId() == positionId) {
                foundBuilding = building;
            }
        }
        buildingService.remove(foundBuilding);
        park.removeBuilding(foundBuilding);
        park.addMoney(foundBuilding.getBuildingType().getCost() * 0.7);
        parkRepository.save(park);
    }
}
