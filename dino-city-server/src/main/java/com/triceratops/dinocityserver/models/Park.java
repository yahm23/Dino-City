package com.triceratops.dinocityserver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name =  "parks")
public class Park {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name="money")
    private double money;

    @Column(name="rating")
    private double rating;

    @JsonIgnoreProperties("park")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @OneToMany(mappedBy = "park",fetch = FetchType.LAZY)
    private List<Enclosure> enclosures;

    @JsonIgnoreProperties("park")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @OneToMany(mappedBy = "park",fetch = FetchType.LAZY)
    private List<Building> buildings;


    public void setName(String name) {
        this.name = name;
    }

    public Park(String name, double money, List<Enclosure> enclosures, List<Building> buildings) {
        this.name = name;
        this.money = money;
        this.enclosures = enclosures;
        this.buildings = buildings;
        this.rating = 1;
    }

    public Park() {
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public List<Enclosure> getEnclosures() {
        return enclosures;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void addMoney(double amount) {this.money += amount;}

    public void reduceMoney(double amount){
        this.money-=amount;
    }

    public void setEnclosures(List<Enclosure> enclosures) {
        this.enclosures = enclosures;
    }

    public void addEnclosure(Enclosure enclosure){
        this.enclosures.add(enclosure);
    }

    public void addBulding(Building building) {
        this.buildings.add(building);
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public void removeBuilding(Building foundBuilding) {
        this.buildings.remove(foundBuilding);
    }
}
