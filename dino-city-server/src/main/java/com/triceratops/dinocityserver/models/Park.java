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

    @JsonIgnoreProperties("park")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @OneToMany(mappedBy = "park",fetch = FetchType.LAZY)
    private List<Enclosure> enclosures;


    public void setName(String name) {
        this.name = name;
    }

    public Park(double money, String name, List<Enclosure> enclosures) {
        this.money = money;
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

    public List<Enclosure> getEnclosures() {
        return enclosures;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void reduceMoney(double amount){
        this.money-=amount;
    }

    public void setEnclosures(List<Enclosure> enclosures) {
        this.enclosures = enclosures;
    }

    public void addEnclosure(Enclosure enclosure){
        this.enclosures.add(enclosure);
    }

}
