package com.triceratops.dinocityserver.models;

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

    @Column(name="enclosures")
    private List<Enclosure> enclosures;

    private double initialMoney = 10000.00;

    public Park( String name, List<Enclosure> enclosures) {
        this.money = initialMoney;
        this.enclosures = enclosures;
        this.name = name;
    }

    public Park() {
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setEnclosures(List<Enclosure> enclosures) {
        this.enclosures = enclosures;
    }

    public double calculateIncome() {
        double income = this.getMoney() - initialMoney;
        return income;
    }

    public int calculatePopulation() {
        int counter = 0;
        for(Enclosure enclosure: enclosures){
            counter += enclosure.getDinosaurs().size();
        }

        return counter;
    }
}
